# $Id: Signal.pm,v 1.4 1998-10-27 16:16:13-05 roderick Exp $
#
# Copyright (c) 1997 Roderick Schertler.  All rights reserved.  This
# program is free software; you can redistribute it and/or modify it
# under the same terms as Perl itself.

package IPC::Signal;

use 5.003_94;	# __PACKAGE__
use strict;
use vars	qw($VERSION @ISA @EXPORT_OK $AUTOLOAD %Sig_num @Sig_name);

require Exporter;

$VERSION	= '1.00';
@ISA		= qw(Exporter);
@EXPORT_OK	= qw(sig_num sig_name sig_translate_setup %Sig_num @Sig_name);
%Sig_num	= ();
@Sig_name	= ();

sub sig_num  ($);
sub sig_name ($);

sub sig_translate_setup () {
    return if %Sig_num && @Sig_name;

    require Config;

    # In 5.005 the sig_num entries are comma separated and there's a
    # trailing 0.
    my $num = $Config::Config{'sig_num'};
    if ($num =~ s/,//g) {
	$num =~ s/\s+0$//;
    }

    my @name	= split ' ', $Config::Config{'sig_name'};
    my @num	= split ' ', $num;

    @name			or die 'No signals defined';
    @name == @num		or die 'Signal name/number mismatch';

    @Sig_num{@name} = @num;
    keys %Sig_num == @name	or die 'Duplicate signal names present';
    for (@name) {
	$Sig_name[$Sig_num{$_}] = $_
	    unless defined $Sig_name[$Sig_num{$_}];
    }
}

# This autoload routine just is just for sig_num() and sig_name().  It
# calls sig_translate_setup() and then snaps the real function definitions
# into place.

sub AUTOLOAD {
    if ($AUTOLOAD ne __PACKAGE__ . '::sig_num'
	    && $AUTOLOAD ne __PACKAGE__ . '::sig_name') {
	require Carp;
	Carp::croak("Undefined subroutine &$AUTOLOAD called");
    }
    sig_translate_setup;
    *sig_num  = sub ($) { $Sig_num{$_[0]} };
    *sig_name = sub ($) { $Sig_name[$_[0]] };
    goto &$AUTOLOAD;
}

1

__END__

=head1 NAME

IPC::Signal - Utility functions dealing with signals

=head1 SYNOPSIS

    $number = sig_num $name;
    $name   = sig_name $number;

    sig_translate_setup;
    $number = $Sig_num{$name};
    $name   = $Sig_name[$number];

=head1 DESCRIPTION

This module contains utility functions for dealing with signals.

Nothing is exported by default.

=over

=item B<sig_num> I<chopped-signal-name>

Returns the signal number of the signal whose name (sans C<SIG>) is
I<chopped-signal-name>, or undef if there is no such signal.

This function is prototyped to take a single scalar argument.

=item B<sig_name> I<signal-number>

Returns the chopped signal name (like C<HUP>) of signal number
I<signal-number>, or undef if there is no such signal.

This function is prototyped to take a single scalar argument.

=item B<sig_translate_setup>

If you want to use the @Sig_name and %Sig_num variables directly you must
call B<sig_translate_setup> to initialize them.  This isn't necessary if
you only use the function interfaces sig_name() and sig_num().

This function is prototyped to take no arguments.

=item B<%Sig_num>

A hash with chopped signal name keys (like C<HUP>) and integer signal
number values.

=item B<@Sig_name>

An array mapping signal numbers to chopped signal names (like C<HUP>).

=back

=head1 AUTHOR

Roderick Schertler <F<roderick@argon.org>>

=head1 SEE ALSO

perl(1).

=cut
