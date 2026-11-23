require pstack.inc

SRC_URI = "git://github.com/peadar/pstack.git;branch=master;protocol=https;tag=v${PV} \
           file://0001-reader.h-include-cstdint-for-uintmax_t-intmax_t.patch \
          "
SRCREV = "6bb443b3fe8f6515cdae33b908780627aac92b96"

UPSTREAM_CHECK_GITTAGREGEX = "v(?P<pver>\d+(\.\d+)+)"
