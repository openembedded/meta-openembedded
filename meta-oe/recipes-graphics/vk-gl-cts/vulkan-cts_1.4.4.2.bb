DESCRIPTION = "Vulkan CTS"

require khronos-cts.inc

SRCREV_vk-gl-cts = "aa481ab6eb66a1c74768fabec453ea6e60f0ee0e"

require vulkan-cts-sources.inc

SRC_URI:append = " \
    file://0001-decode-fix-build-on-ARMv7-targets.patch;patchdir=external/vulkan-video-samples/src \
    file://0001-decode-fix-building-for-non-NEON-enabled-ARM-targets.patch;patchdir=external/vulkan-video-samples/src \
"

# Workaround an optimization bug that breaks createMeshShaderMiscTestsEXT
OECMAKE_CXX_FLAGS:remove:toolchain-gcc = "-O2"

REQUIRED_DISTRO_FEATURES = "vulkan"
inherit features_check

DEPENDS += " vulkan-loader"

EXTRA_OECMAKE += "-DSELECTED_BUILD_TARGETS="deqp-vk deqp-vksc""

do_install() {
	install -d ${D}/${CTSDIR}/mustpass
	cp -r ${B}/external/vulkancts/modules/vulkan/* ${D}/${CTSDIR}/
        cp -r ${S}/external/vulkancts/mustpass/main/ ${D}/${CTSDIR}/mustpass/
	rm -rf ${D}/${CTSDIR}/*.a ${D}/${CTSDIR}/cmake_install.cmake ${D}/${CTSDIR}/CMakeFiles
	rm -rf ${D}/${CTSDIR}/*/*.a ${D}/${CTSDIR}/*/cmake_install.cmake ${D}/${CTSDIR}/*/CMakeFiles
}
