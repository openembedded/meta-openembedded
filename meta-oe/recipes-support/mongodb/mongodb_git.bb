SUMMARY = "mongodb"
LICENSE = "AGPL-3.0 & Apache-2.0"
LIC_FILES_CHKSUM = "file://GNU-AGPL-3.0.txt;md5=73f1eb20517c55bf9493b7dd6e480788 \
                    file://APACHE-2.0.txt;md5=3b83ef96387f14655fc854ddc3c6bd57"

DEPENDS = "openssl libpcre boost libpcap"
# Mongo uses tcmalloc on x86_64, which is provided by gperftools
DEPENDS_append_x86-64 = " gperftools"

inherit scons

PV = "2.6.0+git${SRCPV}"
SRCREV = "be1905c24c7e5ea258e537fbf0d2c502c4fc6de2"
SRC_URI = "git://github.com/mongodb/mongo.git;branch=v2.6 \
           file://0001-Make-it-possible-to-disable-the-use-of-v8.patch \
           file://0002-Fix-linking-when-scripting-is-disabled.patch \
           file://0003-Do-not-build-mongo-binary-when-scripting-is-disabled.patch \
           file://0004-replace-os.uname-with-os.getenv-OE_TARGET_ARCH.patch \
           file://0005-GCC-4.7-supports-atomic-ops-for-armv5-and-up-but-onl.patch \
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


