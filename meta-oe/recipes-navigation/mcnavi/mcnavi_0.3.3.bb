DESCRIPTION = "Free GPS navigation for car and outdoor with OpenStreetMap maps"
HOMEPAGE = "http://www.gps-routes.info/index.php?name=Content&pa=showpage&pid=1"
LICENSE = "GPLv3"
LIC_FILES_CHKSUM = "file://COPYING;md5=8f0e2cd40e05189ec81232da84bd6e1a"
DEPENDS = "ecore evas imlib2 gpsd edje-native mysql5"

SRC_URI = "http://www.gps-routes.info/debian/pool/main/m/mcnavi/mcnavi_${PV}.tar.gz"
SRC_URI[md5sum] = "acc07debcdb3ef448b95e6a6cfcf53d8"
SRC_URI[sha256sum] = "d3ce1f5901f13985d877b2b6ad06b64094ba43a4e13cbfe06c7cb2f1abdda309"

S = "${WORKDIR}/${PN}"

do_configure_prepend() {
  # for some reason our mysql_config returns just sysroot/include_dir while mysql.h is in include_dir/mysql
  sed -i 's#`mysql_config --include`#`mysql_config --include`/mysql#g' ${S}/configure.ac
}

inherit autotools
