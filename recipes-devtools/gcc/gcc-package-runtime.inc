PACKAGES = "\
  libstdc++ \
  libstdc++-precompile-dev \
  libstdc++-dev \
  libg2c \
  libg2c-dev \
  libssp \
  libssp-dev \
  libgfortran \
  libgfortran-dev \
  libmudflap \
  libmudflap-dev \
"

FILES_libg2c = "${target_libdir}/libg2c.so.*"
FILES_libg2c-dev = "\
  ${libdir}/libg2c.so \
  ${libdir}/libg2c.a \
  ${libdir}/libfrtbegin.a"

FILES_libstdc++ = "${libdir}/libstdc++.so.*"
FILES_libstdc++-dev = "\
  ${includedir}/c++/ \
  ${libdir}/libstdc++.so \
  ${libdir}/libstdc++.la \
  ${libdir}/libstdc++.a \
  ${libdir}/libsupc++.la \
  ${libdir}/libsupc++.a"

FILES_libstdc++-precompile-dev = "${includedir}/c++/${TARGET_SYS}/bits/*.gch"

FILES_libssp = "${libdir}/libssp.so.*"
FILES_libssp-dev = " \
  ${libdir}/libssp*.so \
  ${libdir}/libssp*.a \
  ${libdir}/libssp*.la \
  ${libdir}/gcc/${TARGET_SYS}/${BINV}/include/ssp"

FILES_libgfortran = "${libdir}/libgfortran.so.*"
FILES_libgfortran-dev = " \
  ${libdir}/libgfortran.a \
  ${libdir}/libgfortran.so \
  ${libdir}/libgfortranbegin.a"

FILES_libmudflap = "${libdir}/libmudflap*.so.*"
FILES_libmudflap-dev = "\
  ${libdir}/libmudflap*.so \
  ${libdir}/libmudflap*.a \
  ${libdir}/libmudflap*.la"

