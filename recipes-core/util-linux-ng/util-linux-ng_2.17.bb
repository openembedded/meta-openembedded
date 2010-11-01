require util-linux-ng.inc

PR = "${INC_PR}.2"

SRC_URI += "file://uclibc-compile.patch \
	    file://util-linux-ng-replace-siginterrupt.patch \
	    file://fdiskbsdlabel.h-nios2.patch \
           "

# fallocate is glibc 2.10, fallocate64 is glibc 2.11
# we need to disable it for older versions
EXTRA_OECONF += "ac_cv_func_fallocate=no"
EXTRA_OECONF_virtclass-native += "--disable-fallocate --disable-use-tty-group"

SRC_URI[archive.md5sum] = "11cc8a0138019e7060dd275d47dbc096"
SRC_URI[archive.sha256sum] = "8720f7233394b68d17095707c195ebb014943c1075a18fb5fd21ec108f012be3"
