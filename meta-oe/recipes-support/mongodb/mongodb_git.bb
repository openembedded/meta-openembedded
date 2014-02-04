SUMMARY = "mongodb"
LICENSE = "AGPLv3 & Apache-2.0"
LIC_FILES_CHKSUM = "file://GNU-AGPL-3.0.txt;md5=73f1eb20517c55bf9493b7dd6e480788 \
                    file://APACHE-2.0.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "openssl libpcre boost"
# Mongo uses tcmalloc on x86_64, which is provided by gperftools
DEPENDS_append_x86-64 = " gperftools"

inherit scons

PV = "2.5.5+git${SRCPV}"
SRCREV = "588dc81b0822ebb46f80e152b94527a882e6ea5e"
SRC_URI = "git://github.com/mongodb/mongo.git \
           file://0001-Make-it-possible-to-disable-the-use-of-v8.patch \
           file://0002-Fix-linking-when-scripting-is-disabled.patch \
           file://0003-Do-not-build-mongo-binary-when-scripting-is-disabled.patch \
           file://0001-replace-os.uname-with-os.getenv-TARGET_ARCH.patch \
          "

S = "${WORKDIR}/git"

export OE_TARGET_ARCH="${TARGET_ARCH}"

EXTRA_OESCONS = "--prefix=${D}${prefix} \
                 --propagate-shell-environment \
                 --cc-use-shell-environment \
                 --cxx-use-shell-environment \
                 --ld='${TARGET_PREFIX}g++' \
                 --ssl \
                 --use-system-pcre \ 
                 --use-system-boost \
                 --use-system-tcmalloc \
                 --disable-scripting \
                 --nostrip \
                 mongod mongos"


