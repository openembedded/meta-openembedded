SUMMARY = "A simple daemon to allow session software to update firmware"
LICENSE = "LGPL-2.1+"
LIC_FILES_CHKSUM = "file://COPYING;md5=4fbd65380cdd255951079008b364516c"

DEPENDS = "glib-2.0 libxmlb json-glib sqlite3 libjcat gcab vala-native"

SRC_URI = "https://github.com/${BPN}/${BPN}/releases/download/1.7.1/${BP}.tar.xz \
           file://run-ptest \
           file://no-bash.patch \
           file://io.patch"
SRC_URI[sha256sum] = "ae56ceb44b786f21f54d133ac70bc5d9cb8cd4bda0167339c669a228da67fa3c"

UPSTREAM_CHECK_URI = "https://github.com/${BPN}/${BPN}/releases"

# Machine-specific as we examine MACHINE_FEATURES to decide whether to build the UEFI plugins
PACKAGE_ARCH = "${MACHINE_ARCH}"

inherit meson vala gobject-introspection systemd bash-completion pkgconfig gi-docgen ptest manpages

GIDOCGEN_MESON_OPTION = 'docs'
GIDOCGEN_MESON_ENABLE_FLAG = 'docgen'
GIDOCGEN_MESON_DISABLE_FLAG = 'none'

PACKAGECONFIG ??= "curl gnutls gudev gusb lzma \
                   ${@bb.utils.filter('DISTRO_FEATURES', 'polkit systemd', d)} \
                   ${@bb.utils.contains('MACHINE_FEATURES', 'efi', 'plugin_uefi_capsule plugin_uefi_pk', '', d)} \
                   ${@bb.utils.contains('PTEST_ENABLED', '1', 'tests plugin_dummy', '', d)} \
                   plugin_altos \
                   plugin_amt \
                   plugin_emmc \
                   plugin_fastboot \
                   plugin_flashrom \
                   plugin_intel_spi \
                   plugin_logitech_bulkcontroller \
                   plugin_modem_manager \
                   plugin_msr \
                   plugin_nvme \
                   plugin_parade_lspcon \
                   plugin_platform_integrity \
                   plugin_realtek_mst \
                   plugin_redfish \
                   plugin_synaptics_mst \
                   plugin_synaptics_rmi \
                   plugin_thunderbolt"

PACKAGECONFIG[bluez] = "-Dbluez=true,-Dbluez=false"
PACKAGECONFIG[consolekit] = "-Dconsolekit=true,-Dconsolekit=false,consolekit"
PACKAGECONFIG[curl] = "-Dcurl=true,-Dcurl=false,curl"
PACKAGECONFIG[firmware-packager] = "-Dfirmware-packager=true,-Dfirmware-packager=false"
PACKAGECONFIG[gnutls] = "-Dgnutls=true,-Dgnutls=false,gnutls"
PACKAGECONFIG[gudev] = "-Dgudev=true,-Dgudev=false,libgudev"
PACKAGECONFIG[gusb] = "-Dgusb=true,-Dgusb=false,libgusb"
PACKAGECONFIG[libarchive] = "-Dlibarchive=true,-Dlibarchive=false,libarchive"
PACKAGECONFIG[lzma] = "-Dlzma=true,-Dlzma=false,xz"
PACKAGECONFIG[manpages] = "-Dman=true,-Dman=false"
PACKAGECONFIG[polkit] = "-Dpolkit=true,-Dpolkit=false,polkit"
PACKAGECONFIG[systemd] = "-Dsystemd=true,-Dsystemd=false,systemd"
PACKAGECONFIG[tests] = "-Dtests=true,-Dtests=false,gcab-native"

# TODO plugins-all meta-option that expands to all plugin_*?
PACKAGECONFIG[plugin_altos] = "-Dplugin_altos=true,-Dplugin_altos=false,elfutils"
PACKAGECONFIG[plugin_amt] = "-Dplugin_amt=true,-Dplugin_amt=false"
PACKAGECONFIG[plugin_dell] = "-Dplugin_dell=true,-Dplugin_dell=false,libsmbios"
PACKAGECONFIG[plugin_dummy] = "-Dplugin_dummy=true,-Dplugin_dummy=false"
PACKAGECONFIG[plugin_emmc] = "-Dplugin_emmc=true,-Dplugin_emmc=false"
PACKAGECONFIG[plugin_fastboot] = "-Dplugin_fastboot=true,-Dplugin_fastboot=false"
PACKAGECONFIG[plugin_flashrom] = "-Dplugin_flashrom=true,-Dplugin_flashrom=false,flashrom"
PACKAGECONFIG[plugin_intel_spi] = "-Dplugin_intel_spi=true,-Dplugin_intel_spi=false"
PACKAGECONFIG[plugin_logitech_bulkcontroller] = "-Dplugin_logitech_bulkcontroller=true,-Dplugin_logitech_bulkcontroller=false,protobuf-c-native protobuf-c"
PACKAGECONFIG[plugin_modem_manager] = "-Dplugin_modem_manager=true,-Dplugin_modem_manager=false,libqmi modemmanager"
PACKAGECONFIG[plugin_msr] = "-Dplugin_msr=true,-Dplugin_msr=false,cpuid"
PACKAGECONFIG[plugin_nvme] = "-Dplugin_nvme=true,-Dplugin_nvme=false"
PACKAGECONFIG[plugin_parade_lspcon] = "-Dplugin_parade_lspcon=true,-Dplugin_parade_lspcon=false"
PACKAGECONFIG[plugin_platform_integrity] = "-Dplugin_platform_integrity=true,-Dplugin_platform_integrity=false"
PACKAGECONFIG[plugin_realtek_mst] = "-Dplugin_realtek_mst=true,-Dplugin_realtek_mst=false"
PACKAGECONFIG[plugin_redfish] = "-Dplugin_redfish=true,-Dplugin_redfish=false"
PACKAGECONFIG[plugin_synaptics_mst] = "-Dplugin_synaptics_mst=true,-Dplugin_synaptics_mst=false"
PACKAGECONFIG[plugin_synaptics_rmi] = "-Dplugin_synaptics_rmi=true,-Dplugin_synaptics_rmi=false"
PACKAGECONFIG[plugin_thunderbolt] = "-Dplugin_thunderbolt=true,-Dplugin_thunderbolt=false"
PACKAGECONFIG[plugin_tpm] = "-Dplugin_tpm=true,-Dplugin_tpm=false,tpm2-tss"
# Turn off the capsule splash as it needs G-I at buildtime, which isn't currently supported
PACKAGECONFIG[plugin_uefi_capsule] = "-Dplugin_uefi_capsule=true -Dplugin_uefi_capsule_splash=false,-Dplugin_uefi_capsule=false,efivar fwupd-efi"
PACKAGECONFIG[plugin_uefi_pk] = "-Dplugin_uefi_pk=true,-Dplugin_uefi_pk=false"

# Always disable these plugins on non-x86 platforms as they don't compile
DISABLE_NON_X86 = "plugin_msr plugin_intel_spi"
DISABLE_NON_X86:x86 = ""
DISABLE_NON_X86:x86-64 = ""
PACKAGECONFIG:remove = "${DISABLE_NON_X86}"

FILES:${PN} += "${libdir}/fwupd-plugins-* \
                ${systemd_unitdir} \
                ${datadir}/fish \
                ${datadir}/metainfo \
                ${datadir}/icons \
                ${datadir}/dbus-1 \
                ${datadir}/polkit-1 \
                ${nonarch_libdir}/modules-load.d"

FILES:${PN}-ptest += "${libexecdir}/installed-tests/ \
                      ${datadir}/installed-tests/"
RDEPENDS:${PN}-ptest += "gnome-desktop-testing"
