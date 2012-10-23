require ${BPN}.inc

PR = "${INC_PR}.0"

SRC_URI = "\
  ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
  file://fix-ecore-fb-initialization.patch \
"

SRC_URI[md5sum] = "9d668311a42eb05aef57c2e8ce233722"
SRC_URI[sha256sum] = "f69e132bb945c2b968aa17e7ac97f2b21e01b94eb25af2b96d89999c8d63ea60"
