# configure righteously complains:
# | configure:3479: using CFLAGS:  -O2 -pipe -g -feliminate-unused-debug-types -fstack-protector-strong -pie -fpie -D_FORTIFY_SOURCE=2
# | configure:3485: CFLAGS error: CFLAGS may only be used to specify C compiler flags, not macro definitions. Use CPPFLAGS for: -D_FORTIFY_SOURCE=2
# | configure:3516: error: Can not continue. Fix errors mentioned immediately above this line.
TARGET_CFLAGS_remove_pn-c-ares = "${lcl_maybe_fortify}"
TARGET_CPPFLAGS_append_pn-c-ares = "${lcl_maybe_fortify}"
