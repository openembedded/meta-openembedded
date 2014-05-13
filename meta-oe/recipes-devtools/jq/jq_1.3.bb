SUMMARY = "Lightweight and flexible command-line JSON processor"
DESCRIPTION = "jq is like sed for JSON data, you can use it to slice and \
               filter and map and transform structured data with the same \
               ease that sed, awk, grep and friends let you play with text."
HOMEPAGE = "http://stedolan.github.io/jq/"
BUGTRACKER = "https://github.com/stedolan/jq/issues"
SECTION = "utils"

LICENSE = "MIT"
LIC_FILES_CHKSUM = "file://COPYING;md5=244a1fb9cf472474a062d67069dec653"

DEPENDS = "flex-native bison-native"

SRC_URI = "http://stedolan.github.io/${BPN}/download/source/${BP}.tar.gz \
           file://automake-once-fix.patch \
"
SRC_URI[md5sum] = "26081b05d22525eca5cbdd8f9f4db17d"
SRC_URI[sha256sum] = "623f23c36abfc1d96d85020cf421b56f90a229d566f26a4a0d3e8536244bfed7"

inherit autotools

# Don't build documentation (generation requires ruby)
EXTRA_OECONF = "--disable-docs"
