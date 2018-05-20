include nano.inc

do_install_append_libc-musl () {
	rm -rf ${D}${libdir}/charset.alias
	rmdir ${D}${libdir}
}
SRC_URI[md5sum] = "e0c6d76c93932f6c41c40842952495f7"
SRC_URI[sha256sum] = "b64ab017305b1777e97b5b9b07b31db8aeebfc3e8719f61e8da1cf3866d344bd"
