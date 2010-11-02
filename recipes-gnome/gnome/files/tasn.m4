dnl Autoconf macros for libtasn1
dnl $id$

# Modified for LIBTASN1 -- nmav
# Configure paths for LIBGCRYPT
# Shamelessly stolen from the one of XDELTA by Owen Taylor
# Werner Koch   99-12-09

dnl AM_PATH_LIBTASN1([MINIMUM-VERSION, [ACTION-IF-FOUND [, ACTION-IF-NOT-FOUND ]]])
dnl Test for libtasn1, and define LIBTASN1_CFLAGS and LIBTASN1_LIBS
dnl
AC_DEFUN([AM_PATH_LIBTASN1],
[dnl
dnl Get the cflags and libraries from the libtasn1-config script
dnl
AC_ARG_WITH(libtasn1-prefix,
          [  --with-libtasn1-prefix=PFX   Prefix where libtasn1 is installed (optional)],
          libtasn1_config_prefix="$withval", libtasn1_config_prefix="")

  if test x$libtasn1_config_prefix != x ; then
     if test x${LIBTASN1_CONFIG+set} != xset ; then
        LIBTASN1_CONFIG=$libtasn1_config_prefix/bin/libtasn1-config
     fi
  fi

  AC_PATH_PROG(LIBTASN1_CONFIG, libtasn1-config, no)
  min_libtasn1_version=ifelse([$1], ,0.1.0,$1)
  AC_MSG_CHECKING(for libtasn1 - version >= $min_libtasn1_version)
  no_libtasn1=""
  if test "$LIBTASN1_CONFIG" = "no" ; then
    no_libtasn1=yes
  else
    LIBTASN1_CFLAGS=`$LIBTASN1_CONFIG $libtasn1_config_args --cflags`
    LIBTASN1_LIBS=`$LIBTASN1_CONFIG $libtasn1_config_args --libs`
    libtasn1_config_version=`$LIBTASN1_CONFIG $libtasn1_config_args --version`


      ac_save_CFLAGS="$CFLAGS"
      ac_save_LIBS="$LIBS"
      CFLAGS="$CFLAGS $LIBTASN1_CFLAGS"
      LIBS="$LIBS $LIBTASN1_LIBS"
dnl
dnl Now check if the installed libtasn1 is sufficiently new. Also sanity
dnl checks the results of libtasn1-config to some extent
dnl
      rm -f conf.libtasn1test
      AC_TRY_RUN([
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libtasn1.h>

int
main ()
{
    system ("touch conf.libtasn1test");

    if( strcmp( asn1_check_version(NULL), "$libtasn1_config_version" ) )
    {
      printf("\n*** 'libtasn1-config --version' returned %s, but LIBTASN1 (%s)\n",
             "$libtasn1_config_version", asn1_check_version(NULL) );
      printf("*** was found! If libtasn1-config was correct, then it is best\n");
      printf("*** to remove the old version of LIBTASN1. You may also be able to fix the error\n");
      printf("*** by modifying your LD_LIBRARY_PATH enviroment variable, or by editing\n");
      printf("*** /etc/ld.so.conf. Make sure you have run ldconfig if that is\n");
      printf("*** required on your system.\n");
      printf("*** If libtasn1-config was wrong, set the environment variable LIBTASN1_CONFIG\n");
      printf("*** to point to the correct copy of libtasn1-config, and remove the file config.cache\n");
      printf("*** before re-running configure\n");
    }
    else if ( strcmp(asn1_check_version(NULL), LIBTASN1_VERSION ) )
    {
      printf("\n*** LIBTASN1 header file (version %s) does not match\n", LIBTASN1_VERSION);
      printf("*** library (version %s)\n", asn1_check_version(NULL) );
    }
    else
    {
      if ( asn1_check_version( "$min_libtasn1_version" ) )
      {
        return 0;
      }
     else
      {
        printf("no\n*** An old version of LIBTASN1 (%s) was found.\n",
                asn1_check_version(NULL) );
        printf("*** You need a version of LIBTASN1 newer than %s. The latest version of\n",
               "$min_libtasn1_version" );
        printf("*** LIBTASN1 is always available from ftp://gnutls.hellug.gr/pub/gnutls/libtasn1.\n");
        printf("*** \n");
        printf("*** If you have already installed a sufficiently new version, this error\n");
        printf("*** probably means that the wrong copy of the libtasn1-config shell script is\n");
        printf("*** being found. The easiest way to fix this is to remove the old version\n");
        printf("*** of LIBTASN1, but you can also set the LIBTASN1_CONFIG environment to point to the\n");
        printf("*** correct copy of libtasn1-config. (In this case, you will have to\n");
        printf("*** modify your LD_LIBRARY_PATH enviroment variable, or edit /etc/ld.so.conf\n");
        printf("*** so that the correct libraries are found at run-time))\n");
      }
    }
  return 0;
}
],, no_libtasn1=yes,[echo $ac_n "cross compiling; assumed OK... $ac_c"])
       CFLAGS="$ac_save_CFLAGS"
       LIBS="$ac_save_LIBS"
  fi

  if test "x$no_libtasn1" = x ; then
     AC_MSG_RESULT(yes)
     ifelse([$2], , :, [$2])
  else
     if test -f conf.libtasn1test ; then
        :
     else
        AC_MSG_RESULT(no)
     fi
     if test "$LIBTASN1_CONFIG" = "no" ; then
       echo "*** The libtasn1-config script installed by LIBTASN1 could not be found"
       echo "*** If LIBTASN1 was installed in PREFIX, make sure PREFIX/bin is in"
       echo "*** your path, or set the LIBTASN1_CONFIG environment variable to the"
       echo "*** full path to libtasn1-config."
     else
       if test -f conf.libtasn1test ; then
        :
       else
          echo "*** Could not run libtasn1 test program, checking why..."
          CFLAGS="$CFLAGS $LIBTASN1_CFLAGS"
          LIBS="$LIBS $LIBTASN1_LIBS"
          AC_TRY_LINK([
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <libtasn1.h>
],      [ return !!asn1_check_version(NULL); ],
        [ echo "*** The test program compiled, but did not run. This usually means"
          echo "*** that the run-time linker is not finding LIBTASN1 or finding the wrong"
          echo "*** version of LIBTASN1. If it is not finding LIBTASN1, you'll need to set your"
          echo "*** LD_LIBRARY_PATH environment variable, or edit /etc/ld.so.conf to point"
          echo "*** to the installed location  Also, make sure you have run ldconfig if that"
          echo "*** is required on your system"
          echo "***"
          echo "*** If you have an old version installed, it is best to remove it, although"
          echo "*** you may also be able to get things to work by modifying LD_LIBRARY_PATH"
          echo "***" ],
        [ echo "*** The test program failed to compile or link. See the file config.log for the"
          echo "*** exact error that occured. This usually means LIBTASN1 was incorrectly installed"
          echo "*** or that you have moved LIBTASN1 since it was installed. In the latter case, you"
          echo "*** may want to edit the libtasn1-config script: $LIBTASN1_CONFIG" ])
          CFLAGS="$ac_save_CFLAGS"
          LIBS="$ac_save_LIBS"
       fi
     fi
     LIBTASN1_CFLAGS=""
     LIBTASN1_LIBS=""
     ifelse([$3], , :, [$3])
  fi
  rm -f conf.libtasn1test
  AC_SUBST(LIBTASN1_CFLAGS)
  AC_SUBST(LIBTASN1_LIBS)
])

dnl *-*wedit:notab*-*  Please keep this as the last line.

