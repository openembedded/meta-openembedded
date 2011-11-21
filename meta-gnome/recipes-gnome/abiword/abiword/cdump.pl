#!/usr/bin/perl

# This script replace the cdump tool we used to build the ap_wp_Splash.cpp 
# Because the cdump tool has to be run as a part of building the AW package,
# it breaks cross-compilation. A perl script seemed like a generic solution


if ($#ARGV != 1 )
{
	print "Usage: $0 datafile arrayname $#ARGV $ARGV[0] $ARGV[1]\n";
	exit(-1);
}

open FROM, "<$ARGV[0]" or die "Could not open file $ARGV[0]";
binmode FROM;

print "unsigned char $ARGV[1] [] = {\n";

while ($len = read(FROM, $buf, 16))
{
	foreach(split(//,$buf))
	{
		printf("0x%02x,", ord($_));
	}

	print "\n";
}


printf "};\nunsigned long $ARGV[1]_sizeof = sizeof($ARGV[1]);\n";

close FROM;