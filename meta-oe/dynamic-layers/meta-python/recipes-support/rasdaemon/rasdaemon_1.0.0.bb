DESCRIPTION = "Tools to provide a way to get Platform Reliability, Availability and Serviceability (RAS) reports made via the Kernel tracing events"
HOMEPAGE = "https://github.com/mchehab/rasdaemon"
LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://COPYING;md5=d3070efe0afa3dc41608bd82c00bb0dc"

SRC_URI = "git://github.com/mchehab/rasdaemon.git;branch=master;protocol=https;tag=v${PV} \
           file://init"

SRCREV = "83379997794e3ed6f613b91b188c754bc642de5d"
UPSTREAM_CHECK_GITTAGREGEX = "^v(?P<pver>\d+\.\d+\.[0-8]?\d)$"

DEPENDS = "libtraceevent"
DEPENDS:append:libc-musl = " argp-standalone"
LDFLAGS:append:libc-musl = " -largp"
RDEPENDS:${PN} = "python3-core python3-crypt python3-datetime python3-io python3-json \
	python3-logging python3-netclient python3-sqlalchemy"

inherit meson pkgconfig update-rc.d systemd

EXTRA_OEMESON = "-Denable-arch=auto"

PACKAGECONFIG ??= "sqlite3 mce aer extlog devlink diskerror"
PACKAGECONFIG[sqlite3] = "-Dsqlite3=enabled,-Dsqlite3=disabled,sqlite3,python3-sqlite3"
PACKAGECONFIG[mysql] = "-Dmysql=enabled,-Dmysql=disabled,mariadb"
PACKAGECONFIG[postgresql] = "-Dpostgresql=enabled,-Dpostgresql=disabled,postgresql"
PACKAGECONFIG[mce] = "-Dmce=enabled,-Dmce=disabled"
PACKAGECONFIG[erst] = "-Derst=enabled,-Derst=disabled"
PACKAGECONFIG[aer] = "-Daer=enabled,-Daer=disabled,pciutils"
PACKAGECONFIG[pcie-edpc] = "-Dpcie-edpc=enabled,-Dpcie-edpc=disabled,pciutils"
PACKAGECONFIG[extlog] = "-Dextlog=enabled,-Dextlog=disabled"
PACKAGECONFIG[devlink] = "-Ddevlink=enabled,-Ddevlink=disabled"
PACKAGECONFIG[diskerror] = "-Ddiskerror=enabled,-Ddiskerror=disabled"
PACKAGECONFIG[cxl] = "-Dcxl=enabled,-Dcxl=disabled"
PACKAGECONFIG[memory-failure] = "-Dmemory-failure=enabled,-Dmemory-failure=disabled"
PACKAGECONFIG[memory-ce-pfa] = "-Dmemory-ce-pfa=enabled,-Dmemory-ce-pfa=disabled"
PACKAGECONFIG[memory-row-ce-pfa] = "-Dmemory-row-ce-pfa=enabled,-Dmemory-row-ce-pfa=disabled"
PACKAGECONFIG[cpu-fault-isolation] = "-Dcpu-fault-isolation=enabled,-Dcpu-fault-isolation=disabled"
PACKAGECONFIG[signal] = "-Dsignal=enabled,-Dsignal=disabled"
PACKAGECONFIG[abrt-report] = "-Dabrt-report=enabled,-Dabrt-report=disabled"
PACKAGECONFIG[bmc-generic] = "-Dbmc-generic=enabled,-Dbmc-generic=disabled"
PACKAGECONFIG[openbmc-unified-sel] = "-Dopenbmc-unified-sel=enabled,-Dopenbmc-unified-sel=disabled"
PACKAGECONFIG[ampere-oem-sel] = "-Dampere-oem-sel=enabled,-Dampere-oem-sel=disabled"
PACKAGECONFIG[arm] = "-Darm=enabled,-Darm=disabled"
PACKAGECONFIG[reri] = "-Dreri=enabled,-Dreri=disabled"
PACKAGECONFIG[amp-ns-decode] = "-Damp-ns-decode=enabled,-Damp-ns-decode=disabled"
PACKAGECONFIG[hisi-ns-decode] = "-Dhisi-ns-decode=enabled,-Dhisi-ns-decode=disabled"
PACKAGECONFIG[jaguar-ns-decode] = "-Djaguar-ns-decode=enabled,-Djaguar-ns-decode=disabled"
PACKAGECONFIG[nvidia-ns-decode] = "-Dnvidia-ns-decode=enabled,-Dnvidia-ns-decode=disabled"
PACKAGECONFIG[yitian-ns-decode] = "-Dyitian-ns-decode=enabled,-Dyitian-ns-decode=disabled"

do_install:append() {
	if [ "${libdir}/systemd/system" != "${systemd_system_unitdir}" ]; then
		install -d ${D}${systemd_system_unitdir}
		mv ${D}${libdir}/systemd/system/* ${D}${systemd_system_unitdir}/
		rm -rf ${D}${libdir}/systemd
	fi
	install -d ${D}${sysconfdir}/init.d
	install -m 755 ${UNPACKDIR}/init ${D}${sysconfdir}/init.d/rasdaemon
}

FILES:${PN} += "${datadir} \
		${PYTHON_SITEPACKAGES_DIR} \
		${systemd_system_unitdir}"

SYSTEMD_SERVICE:${PN} = "rasdaemon.service"
SYSTEMD_AUTO_ENABLE = "enable"

INITSCRIPT_PACKAGES = "${PN}"
INITSCRIPT_NAME:${PN} = "rasdaemon"
INITSCRIPT_PARAMS:${PN} = "defaults 89"
