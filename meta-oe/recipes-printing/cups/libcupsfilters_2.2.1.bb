DESCRIPTION = "OpenPrinting libcupsfilters"
HOMEPAGE = "https://github.com/OpenPrinting"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://COPYING;md5=aab2024bd2a475438a154cd1640c9684"

DEPENDS = "cups fontconfig libexif dbus lcms qpdf poppler libpng jpeg tiff"

SRC_URI = "https://github.com/OpenPrinting/${BPN}/releases/download/${PV}/${BP}.tar.xz \
           "
SRC_URI[sha256sum] = "0a22b849d5068c4c86b20fbb4192d3faa3dabcc9ee844c8fd73710ed821d4860"

inherit autotools gettext pkgconfig github-releases

# github-releases defaults GITHUB_BASE_URI to github.com/<BPN>/<BPN>, which 404s
# for this project; releases live under the OpenPrinting org.
GITHUB_BASE_URI = "https://github.com/OpenPrinting/${BPN}/releases/"
UPSTREAM_CHECK_REGEX = "releases/tag/(?P<pver>\d+(\.\d+)+)"

FILES:${PN} += "${datadir}"
RDEPENDS:${PN} += "ghostscript"
