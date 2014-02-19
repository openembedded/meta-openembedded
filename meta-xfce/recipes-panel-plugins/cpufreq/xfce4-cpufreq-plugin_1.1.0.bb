SUMMARY = "Panel plugin to display frequency of all cpus"
HOMEPAGE = "http://goodies.xfce.org/projects/panel-plugins/xfce4-cpufreq-plugin"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=1f6f1c0be32491a0c8d2915607a28f36"

inherit xfce-panel-plugin

SRC_URI[md5sum] = "dcc56af0e19266956c297d124ba551f4"
SRC_URI[sha256sum] = "eafa261bf984231ed8487c08decea6d916339d82b52f4bbe748e5c6a95c7f6b6"

# defaults in xfce4-panel-plugin.bbclass don't match so override here
FILES_${PN} = "${datadir}/icons ${datadir}/xfce4 ${libdir}/xfce4/panel/plugins/*.so*"

# *.so are required for plugin detection
INSANE_SKIP_${PN} = "dev-so"
