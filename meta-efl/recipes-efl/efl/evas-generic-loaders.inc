DESCRIPTION = "Evas generic loaders"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=06568fc31423f3c8ad84f375e061c342"
DEPENDS = "eina poppler librsvg cairo"

inherit efl

INC_PR = "r0"

SRCNAME = "evas_generic_loaders"

EXTRA_OECONF = " \
  --enable-poppler \
  --enable-svg \
  --disable-gstreamer \
  --disable-libraw \
  --disable-spectre \
"

python populate_packages_prepend () {
    bb.note( "splitting packages for evas generic loaders" )
    basedir = bb.data.expand( '${libdir}/evas/utils', d )

    do_split_packages(d, basedir, '^evas_image_loader\.(.*)$',
    output_pattern = "evas-generic-loader-%s",
    description = 'Evas generic loaders - module %s',
    allow_dirs=True, recursive=False, extra_depends="" )
}

FILES_evas-generic-loader-xcf += "${libdir}/evas/utils/*.xcf.gz"
FILES_${PN}-dbg += "${libdir}/evas/utils/.debug"

PACKAGES_DYNAMIC += "^evas-generic-loader-.*"
