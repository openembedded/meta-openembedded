require ${BPN}.inc

SRC_URI = "\
    ${E_MIRROR}/${SRCNAME}-${SRCVER}.tar.gz \
    file://fix-ecore-fb-initialization.patch \
"

SRC_URI[md5sum] = "5a8ca096d8c15647b2493d4664a4b895"
SRC_URI[sha256sum] = "26cb1dc02213a221fdec32ef4ea4ece608e5239bdbd19c9d62b09cf931863738"
