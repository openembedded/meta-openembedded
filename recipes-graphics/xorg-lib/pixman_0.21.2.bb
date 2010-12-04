require pixman.inc

SRC_URI[archive.md5sum] = "9e09fd6e58cbf9717140891e0b7d4a7a"
SRC_URI[archive.sha256sum] = "295f51416caf307ff7caf1153ee9b1d86b9f7f02a7876d12db6538d80451c5de"

PR = "${INC_PR}.1"

SRC_URI += "\
           file://0002-Fix-argument-quoting-for-AC_INIT.patch \
           file://0003-Sun-s-copyrights-belong-to-Oracle-now.patch \
           file://0004-C-fast-path-for-a1-fill-operation.patch \
           file://0005-ARM-added-neon_composite_over_n_8_8-fast-path.patch \
           file://0006-ARM-introduced-fetch_mask_pixblock-macro-to-simplify.patch \
           file://0007-ARM-better-NEON-instructions-scheduling-for-over_n_8.patch \
           file://0008-ARM-added-neon_composite_over_8888_n_0565-fast-path.patch \
           file://0009-ARM-reuse-common-NEON-code-for-over_-n_8-8888_n-8888.patch \
           file://0010-ARM-added-neon_composite_over_0565_n_0565-fast-path.patch \
           file://0011-ARM-added-neon_composite_add_8888_8_8888-fast-path.patch \
           file://0012-ARM-better-NEON-instructions-scheduling-for-add_8888.patch \
           file://0013-ARM-added-neon_composite_add_n_8_8888-fast-path.patch \
           file://0014-ARM-added-neon_composite_add_8888_n_8888-fast-path.patch \
           file://0015-ARM-added-flags-parameter-to-some-asm-fast-path-wrap.patch \
           file://0016-ARM-added-neon_composite_in_n_8-fast-path.patch \
           file://0017-add-_pixman_bits_override_accessors.patch \
           file://0018-Generic-C-implementation-of-pixman_blt-with-overlapp.patch \
           file://0019-Support-of-overlapping-src-dst-for-pixman_blt_mmx.patch \
           file://0020-Support-of-overlapping-src-dst-for-pixman_blt_sse2.patch \
           file://0021-Support-of-overlapping-src-dst-for-pixman_blt_neon.patch \
           file://0022-ARM-added-NEON-optimizations-for-fetch-store-r5g6b5-.patch \
           file://0023-ARM-added-NEON-optimizations-for-fetch-store-a8-scan.patch \
           file://0024-ARM-added-NEON-optimizations-for-fetching-x8r8g8b8-s.patch \
"

NEON = " --disable-arm-neon "
NEON_armv7a = " "

EXTRA_OECONF = "${NEON} --disable-gtk"
