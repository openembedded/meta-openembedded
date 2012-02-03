DEPENDS += "python-nativesdk"
RDEPENDS += "python-nativesdk-core python-nativesdk-lang python-nativesdk-re \
			 python-nativesdk-codecs python-nativesdk-netclient"

EXTRA_OECONF_append = "--with-python=${WORKDIR}/python"
PRINC = "1"

do_configure_prepend() {
cat > ${WORKDIR}/python << EOF
#! /bin/sh
case "\$2" in
	--includes) echo "-I${STAGING_DIR}/${HOST_ARCH}-nativesdk${HOST_VENDOR}-${HOST_OS}${exec_prefix}/include/python${PYTHON_BASEVERSION}/" ;;
	--ldflags) echo "-L${STAGING_DIR}/${HOST_ARCH}-nativesdk${HOST_VENDOR}-${HOST_OS}${libdir}/python${PYTHON_BASEVERSION}/config/config -lpthread -ldl -lutil -lm -lpython${PYTHON_BASEVERSION}" ;;
	--exec-prefix) echo "/usr" ;;
	*) exit 1 ;;
esac
exit 0
EOF
	chmod +x ${WORKDIR}/python
}
