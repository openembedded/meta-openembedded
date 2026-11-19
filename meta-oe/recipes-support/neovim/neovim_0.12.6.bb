SUMMARY = "Vim-fork focused on extensibility and usability"
HOMEPAGE = "https://neovim.io"
LICENSE = "Apache-2.0 AND Vim"
LIC_FILES_CHKSUM = "file://LICENSE.txt;md5=05b485c2880cb0b3d7dc27f07456ace1"

DEPENDS = " \
    luajit \
    luajit-native \
    utf8proc \
    libuv \
    luv \
    lpeg \
    libtreesitter \
"
DEPENDS:append:class-target = " neovim-native"

SRC_URI = " \
    git://github.com/neovim/neovim.git;protocol=https;branch=release-0.12;tag=v${PV} \
    file://0001-cmake-allow-omitting-build-paths-from-the-binary.patch \
"
SRCREV = "779c36969e31080503be2dfccbcc3f999e289606"

inherit cmake pkgconfig gettext mime-xdg update-alternatives

EXTRA_OECMAKE = " \
    -DENABLE_UNIBILIUM=OFF \
    -DCMAKE_BUILD_TYPE=RelWithDebInfo \
    -DNVIM_OMIT_VERSION_CFLAGS=ON \
    -DVTERM_TEST_FILE=/tmp/nvim_vterm_test_output \
"
EXTRA_OECMAKE:append:class-native = " -DICONV_INCLUDE_DIR=${STAGING_INCDIR_NATIVE}"
EXTRA_OECMAKE:append:class-target = " \
    -DNVIM_HOST_PRG=${STAGING_BINDIR_NATIVE}/nvim \
    -DNLUA0_HOST_PRG=${STAGING_LIBDIR_NATIVE}/nvim/libnlua0.so \
"

# The target build runs the native nvim and its nlua0 module to generate
# sources and runtime files; upstream does not install nlua0.
do_install:append:class-native() {
    install -d ${D}${libdir}/nvim
    install -m 0755 ${B}/lib/libnlua0.so ${D}${libdir}/nvim/libnlua0.so
}

FILES:${PN} += " \
    ${libdir}/nvim \
    ${datadir}/nvim \
    ${datadir}/applications \
    ${datadir}/icons \
"

ALTERNATIVE:${PN} = "vi vim"
ALTERNATIVE_TARGET = "${bindir}/nvim"
ALTERNATIVE_LINK_NAME[vi] = "${base_bindir}/vi"
ALTERNATIVE_LINK_NAME[vim] = "${bindir}/vim"
ALTERNATIVE_PRIORITY = "95"

RRECOMMENDS:${PN}:class-target += " \
    tree-sitter-c \
    tree-sitter-lua \
    tree-sitter-markdown \
    tree-sitter-query \
    tree-sitter-vim \
    tree-sitter-vimdoc \
"

BBCLASSEXTEND = "native"
