SECTION = "libs"
PRIORITY = "optional"
DEPENDS = "virtual/libx11 pixman libsm libpng fontconfig libxrender glib-2.0"
DESCRIPTION = "Cairo graphics library"
LICENSE = "MPL LGPL"

#check for TARGET_FPU=soft and inform configure of the result so it can disable some floating points 
require cairo-fpu.inc
EXTRA_OECONF += "${@get_cairo_fpu_setting(bb, d)}"

inherit autotools pkgconfig 

PACKAGES =+ "cairo-trace cairo-trace-dev libcairo-script-interpreter libcairo-gobject"
FILES_libcairo-gobject = "${libdir}/libcairo-gobject.so.*"
FILES_libcairo-script-interpreter = "${libdir}/libcairo-script-interpreter.so.*"
FILES_cairo-trace = "${bindir}/*trace ${libdir}/cairo/libcairo-trace.so.*"
FILES_cairo-trace-dev = "${libdir}/cairo/*"

