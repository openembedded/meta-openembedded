# polkit must prepare polkid group
DEPENDS += "polkit"

inherit allarch useradd

do_install_prepend() {
    install -m 700 -d ${D}${sysconfdir}/polkit-1/rules.d
    chown polkitd:polkitd ${D}${sysconfdir}/polkit-1/rules.d
}
