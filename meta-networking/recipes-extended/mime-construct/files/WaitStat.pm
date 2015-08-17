# $Id: WaitStat.pm,v 1.3 1999-10-21 12:39:43-04 roderick Exp $
#
# Copyright (c) 1997 Roderick Schertler.  All rights reserved.  This
# program is free software; you can redistribute it and/or modify it
# under the same terms as Perl itself.

=head1 NAME

Proc::WaitStat - Interpret and act on wait() status values

=head1 SYNOPSIS

    $description = waitstat $?;
    exit waitstat_reuse $?;
    waitstat_die $?, 'program-name';
    close_die COMMAND, 'program-name';

=head1 DESCRIPTION

This module contains functions for interpreting and acting on wait
status values.

Nothing is exported by default.

=over

=cut

package Proc::WaitStat;

use 5.003_98;	# piped close errno resetting
use strict;
use vars	qw($VERSION @ISA @EXPORT_OK);

use Carp	qw(croak);
use Exporter	  ();
use IPC::Signal	qw(sig_name);
use POSIX	qw(:sys_wait_h);

$VERSION	= '1.00';
@ISA		= qw(Exporter);
@EXPORT_OK	= qw(waitstat waitstat_reuse waitstat_die close_die);

=item B<waitstat> I<wait-status>

Returns a string representation of wait() status value I<wait-status>.
Values returned are like C<"0"> and C<"64"> and C<"killed (SIGHUP)">.

This function is prototyped to take a single scalar argument.

=cut

sub waitstat ($) {
    my $status = shift;

    if (WIFEXITED $status) {
	WEXITSTATUS $status
    }
    elsif (WIFSIGNALED $status) {
	# XXX WCOREDUMP
	'killed (SIG' . sig_name(WTERMSIG $status) . ')'
    }
    elsif (WIFSTOPPED $status) {
	'stopped (SIG' . sig_name(WSTOPSIG $status) . ')'
    }
    # XXX WIFCONTINUED
    else {
	"invalid wait status $status"
    }
}

=item B<waitstat_reuse> I<wait-status>

Turn I<wait-status> into a value which can be passed to B<exit>, converted
in the same manner the shell uses.  If I<wait-status> indicates a normal
exit, return the exit value.  If I<wait-status> instead indicates death by
signal, return 128 plus the signal number.

This function is prototyped to take a single scalar argument.

=cut

sub waitstat_reuse ($) {
    my $status = shift;

    if (WIFEXITED $status) {
	WEXITSTATUS $status
    }
    elsif (WIFSIGNALED $status) {
	128 + WTERMSIG $status
    }
    elsif (WIFSTOPPED $status) {
	128 + WSTOPSIG $status
    }
    else {
	croak "Invalid wait status $status";
    }
}

=item B<waitstat_die> I<wait-status> I<program-name>

die() if I<wait-status> is non-zero (mentioning I<program-name> as the
source of the error).

This function is prototyped to take two scalar arguments.

=cut

sub waitstat_die ($$) {
    my ($status, $program) = @_;
    croak "Non-zero exit (" . waitstat($status) .
	    ") from $program"
	if $status;
}

=item B<close_die> I<filehandle> I<name>

Close I<filehandle>, if that fails die() with an appropriate message
which refers to I<name>.  This handles failed closings of both programs
and files properly.

This function is prototyped to take a filehandle (actually, a glob ref)
and a scalar.

=cut

sub close_die (*$) {
    my ($fh, $name) = @_;

    unless (ref $fh || ref \$fh eq 'GLOB') {
	require Symbol;
	$fh = Symbol::qualify_to_ref($fh, caller);
    }

    unless (close $fh) {
	croak "Error closing $name: ",
		$!+0 ? "$!" : 'non-zero exit (' . waitstat($?) . ')';
    }
}

1

__END__

=back

=head1 EXAMPLES

    close SENDMAIL;
    exit if $? == 0;
    log "sendmail failure: ", waitstat $?;
    exit EX_TEMPFAIL;

    $pid == waitpid $pid, 0 or croak "Failed to reap $pid: $!";
    exit waitstat_reuse $?;

    $output = `some-program -with args`;
    waitstat_die $?, 'some-program';
    print "Output from some-process:\n", $output;

    open PROGRAM, '| post-processor' or die "Can't fork: $!";
    while (<IN>) {
    	print PROGRAM pre_process $_
	    or die "Error writing to post-processor: $!";
    }
    # This handles both flush failures at close time and a non-zero exit
    # from the subprocess.
    close_die PROGRAM, 'post-processor';

=head1 AUTHOR

Roderick Schertler <F<roderick@argon.org>>

=head1 SEE ALSO

perl(1), IPC::Signal(3pm).

=cut
