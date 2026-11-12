SUMMARY = "Hunspell dictionaries used by Maliit"

#Provided by Titus Wormer (wooorm) https://github.com/wooorm/dictionaries

# The single package ships all dictionaries, so it carries all their licenses
LICENSE = "${@' AND '.join(sorted(set(['(%s)' % l if ' ' in l else l for l in (d.getVarFlags('HUNSPELL_DICT_LICENSE') or dict()).values()])))}"
HUNSPELL_DICT_LICENSE[bg] = "GPL-2.0-only OR LGPL-2.1-only OR MPL-1.1"
HUNSPELL_DICT_LICENSE[br] = "GPL-2.0-only OR LGPL-2.1-only OR MPL-1.1"
HUNSPELL_DICT_LICENSE[ca] = "GPL-2.0-only OR LGPL-2.1-only"
HUNSPELL_DICT_LICENSE[ca-valencia] = "GPL-2.0-only OR LGPL-2.1-only"
HUNSPELL_DICT_LICENSE[cs] = "GPL-2.0-only"
HUNSPELL_DICT_LICENSE[da] = "GPL-2.0-only OR LGPL-2.1-only OR MPL-1.1"
HUNSPELL_DICT_LICENSE[de] = "GPL-2.0-only OR GPL-3.0-only"
HUNSPELL_DICT_LICENSE[de-AT] = "GPL-2.0-only OR GPL-3.0-only"
HUNSPELL_DICT_LICENSE[de-CH] = "GPL-2.0-only OR GPL-3.0-only"
HUNSPELL_DICT_LICENSE[el] = "GPL-2.0-only OR LGPL-2.1-only OR MPL-1.1"
HUNSPELL_DICT_LICENSE[el-polyton] = "GPL-3.0-only"
HUNSPELL_DICT_LICENSE[en-AU] = "MIT AND LicenseRef-SCOWL"
HUNSPELL_DICT_LICENSE[en-CA] = "MIT AND LicenseRef-SCOWL"
HUNSPELL_DICT_LICENSE[en-GB] = "MIT AND LicenseRef-SCOWL"
HUNSPELL_DICT_LICENSE[en-US] = "MIT AND LicenseRef-SCOWL"
HUNSPELL_DICT_LICENSE[en-ZA] = "LGPL-2.1-only"
HUNSPELL_DICT_LICENSE[eo] = "GPL-2.0-only"
HUNSPELL_DICT_LICENSE[es] = "GPL-3.0-only OR LGPL-3.0-only OR MPL-1.1"
HUNSPELL_DICT_LICENSE[et] = "LGPL-2.1-only"
HUNSPELL_DICT_LICENSE[eu] = "GPL-2.0-only"
HUNSPELL_DICT_LICENSE[fo] = "GPL-2.0-only OR LGPL-2.1-only OR MPL-1.1"
HUNSPELL_DICT_LICENSE[fr] = "MPL-2.0"
HUNSPELL_DICT_LICENSE[fur] = "GPL-2.0-only"
HUNSPELL_DICT_LICENSE[fy] = "GPL-3.0-only"
HUNSPELL_DICT_LICENSE[ga] = "GPL-2.0-only"
HUNSPELL_DICT_LICENSE[gd] = "GPL-3.0-only"
HUNSPELL_DICT_LICENSE[gl] = "GPL-3.0-only"
HUNSPELL_DICT_LICENSE[he] = "AGPL-3.0-only"
HUNSPELL_DICT_LICENSE[hr] = "LGPL-2.1-only OR SISSL"
HUNSPELL_DICT_LICENSE[hu] = "GPL-2.0-only OR LGPL-2.1-only OR MPL-1.1"
HUNSPELL_DICT_LICENSE[hy-arevela] = "GPL-2.0-only OR LGPL-2.1-only OR MPL-1.1"
HUNSPELL_DICT_LICENSE[hy-arevmda] = "GPL-2.0-only OR LGPL-2.1-only OR MPL-1.1"
HUNSPELL_DICT_LICENSE[ia] = "GPL-3.0-only"
HUNSPELL_DICT_LICENSE[ie] = "Apache-2.0"
HUNSPELL_DICT_LICENSE[is] = "CC-BY-SA-3.0 AND CC-BY-4.0"
HUNSPELL_DICT_LICENSE[it] = "GPL-3.0-only"
HUNSPELL_DICT_LICENSE[ko] = "GPL-2.0-only OR LGPL-2.1-only OR MPL-1.1"
HUNSPELL_DICT_LICENSE[la] = "GPL-2.0-only"
HUNSPELL_DICT_LICENSE[lb] = "EUPL-1.1"
HUNSPELL_DICT_LICENSE[lt] = "BSD-3-Clause"
HUNSPELL_DICT_LICENSE[ltg] = "LGPL-2.1-only"
HUNSPELL_DICT_LICENSE[lv] = "LGPL-2.1-only"
HUNSPELL_DICT_LICENSE[mk] = "GPL-3.0-only"
HUNSPELL_DICT_LICENSE[mn] = "GPL-2.0-only"
HUNSPELL_DICT_LICENSE[nb] = "GPL-2.0-only"
HUNSPELL_DICT_LICENSE[nds] = "GPL-3.0-only"
HUNSPELL_DICT_LICENSE[ne] = "LGPL-2.1-only"
HUNSPELL_DICT_LICENSE[nl] = "BSD-3-Clause OR CC-BY-3.0"
HUNSPELL_DICT_LICENSE[nn] = "GPL-2.0-only"
HUNSPELL_DICT_LICENSE[pl] = "GPL-3.0-only OR LGPL-3.0-only OR MPL-2.0"
HUNSPELL_DICT_LICENSE[pt] = "GPL-2.0-only OR LGPL-2.1-only OR MPL-1.1"
HUNSPELL_DICT_LICENSE[pt-BR] = "LGPL-2.1-only"
HUNSPELL_DICT_LICENSE[ro] = "GPL-2.0-only OR LGPL-2.1-only OR MPL-1.1"
HUNSPELL_DICT_LICENSE[ru] = "LicenseRef-hunspell-ru"
HUNSPELL_DICT_LICENSE[rw] = "GPL-3.0-only"
HUNSPELL_DICT_LICENSE[sk] = "GPL-2.0-only"
HUNSPELL_DICT_LICENSE[sl] = "GPL-3.0-only OR LGPL-2.1-only"
HUNSPELL_DICT_LICENSE[sr] = "LGPL-3.0-only"
HUNSPELL_DICT_LICENSE[sr-Latn] = "LGPL-3.0-only"
HUNSPELL_DICT_LICENSE[sv] = "LGPL-3.0-only"
HUNSPELL_DICT_LICENSE[tr] = "MIT"
HUNSPELL_DICT_LICENSE[uk] = "GPL-3.0-only"
HUNSPELL_DICT_LICENSE[vi] = "GPL-3.0-only"
NO_GENERIC_LICENSE[SCOWL] = "dictionaries/en-US/license"
NO_GENERIC_LICENSE[hunspell-ru] = "dictionaries/ru/license"

LIC_FILES_CHKSUM = " \
    file://dictionaries/bg/license;md5=ab14e2ded1a43b84be9aba2be64b9814 \
    file://dictionaries/br/license;md5=2e046c64edc0a4e47fb3ea614e6c82c4 \
    file://dictionaries/ca-valencia/license;md5=da087cefab7bc3b7687203198a0042ec \
    file://dictionaries/ca/license;md5=da087cefab7bc3b7687203198a0042ec \
    file://dictionaries/cs/license;md5=e0496d626a53ad31addea1869c1bed53 \
    file://dictionaries/da/license;md5=b7678b3e997999043be3503dc717d96d \
    file://dictionaries/de-AT/license;md5=a03d823e8cd00fd71c9ba18b8ec4ccf1 \
    file://dictionaries/de-CH/license;md5=a03d823e8cd00fd71c9ba18b8ec4ccf1 \
    file://dictionaries/de/license;md5=a03d823e8cd00fd71c9ba18b8ec4ccf1 \
    file://dictionaries/el-polyton/license;md5=d952bcbf1c67129920f03536197c7281 \
    file://dictionaries/el/license;md5=90b757dd77b1ebbf473eef31037a59e8 \
    file://dictionaries/en-AU/license;md5=dae002c846eab5e1132ba2e00692dc68 \
    file://dictionaries/en-CA/license;md5=6c205de0cbcae4b42cf992bb355a5346 \
    file://dictionaries/en-GB/license;md5=dd6d2bbd7b7f4519448f2b995fd99cd4 \
    file://dictionaries/en-US/license;md5=244a909f295640bfafeb8d9e522d454b \
    file://dictionaries/en-ZA/license;md5=56e4b4ca965de3bba6db42494cc34576 \
    file://dictionaries/eo/license;md5=eb723b61539feef013de476e68b5c50a \
    file://dictionaries/es/license;md5=b41cabf4e6f2a7d951684246518bea4f \
    file://dictionaries/et/readme.md;md5=59d851f12cc280f893b245938abc1905 \
    file://dictionaries/fo/license;md5=fa9ce505d07423c9f8e6c1073f4806c2 \
    file://dictionaries/fr/license;md5=2346c7bffe6ab4d51730ab3116817c72 \
    file://dictionaries/fur/license;md5=28ac8c6e75d6c44b60016a8c850036e3 \
    file://dictionaries/fy/license;md5=c05115c38ca2a4aa95cb9c9b00ed78df \
    file://dictionaries/ga/license;md5=ee7d601b5d3be3856a169afa71eb3376 \
    file://dictionaries/gd/license;md5=b5f39c26d691733095323a59d0f02216 \
    file://dictionaries/gl/license;md5=d32239bcb673463ab874e80d47fae504 \
    file://dictionaries/he/license;md5=e627dcdf6878e7eca4454e39d1489271 \
    file://dictionaries/hr/license;md5=4b03c9b6e41a6ef12b4e31d2fb224d46 \
    file://dictionaries/hu/license;md5=c9755266de74fdbe69636bdb3076b603 \
    file://dictionaries/hy-arevela/license;md5=c690b6bc807de0a6dcd62db030843ee4 \
    file://dictionaries/hy-arevmda/license;md5=c690b6bc807de0a6dcd62db030843ee4 \
    file://dictionaries/ia/license;md5=25c37bbb54f9ab5da9b4bcdda2e2f68e \
    file://dictionaries/ie/license;md5=fa818a259cbed7ce8bc2a22d35a464fc \
    file://dictionaries/is/license;md5=db5e38a312b91d717561514767c95999 \
    file://dictionaries/it/license;md5=31d06285633a89f4d0267895ef8b7107 \
    file://dictionaries/ko/license;md5=5ff35d5b0523cccb3049edff77c73a10 \
    file://dictionaries/la/license;md5=9bc08ae2930eb806cee44bbae905b7ba \
    file://dictionaries/lb/license;md5=5d6417331778645577c40c3329a3d559 \
    file://dictionaries/lt/license;md5=3d1264d7017271c6731fefaba912a15f \
    file://dictionaries/ltg/license;md5=72c786f9d43bb07c101766457fcf6435 \
    file://dictionaries/lv/license;md5=957d42bd58feff079daa6ede1d215585 \
    file://dictionaries/mk/license;md5=c05a3747d4c871f3c6378909668ec72d \
    file://dictionaries/mn/license;md5=93a1be573a1bd039cb4e17c21ed800a7 \
    file://dictionaries/nb/license;md5=6b50c67a5ee9ba5af1b4ab6c009b66f2 \
    file://dictionaries/nds/license;md5=08c69ee106e67a1085d45dc723ae5396 \
    file://dictionaries/ne/license;md5=620e39f1aec72b0bdbc922128dd08267 \
    file://dictionaries/nl/license;md5=f9181fe437dd701ce6ae690f6e09b119 \
    file://dictionaries/nn/license;md5=9d372c99817cb42bdd1f6c0acb7f4d11 \
    file://dictionaries/pl/license;md5=5d0961c4eee8448d5f3738b1ff04ebe8 \
    file://dictionaries/pt-BR/license;md5=08a7e74543d3e612e7613f2959bedab0 \
    file://dictionaries/pt/license;md5=53d8ed71b4053ef0ccfcf6b3f89880a8 \
    file://dictionaries/ro/license;md5=db18b1d6cfc0d4ca32371cb76900de2c \
    file://dictionaries/ru/license;md5=a3d4108679bd3b76ec1a86520b5362cf \
    file://dictionaries/rw/license;md5=784d7dc7357bd924e8d5642892bf1b6b \
    file://dictionaries/sk/license;md5=7fb3956bf960db433deedec7746d33c5 \
    file://dictionaries/sl/license;md5=4965d53062ea2045837facf483475ac7 \
    file://dictionaries/sr-Latn/license;md5=0139a4b8ce316882cceae8924030950d \
    file://dictionaries/sr/license;md5=0139a4b8ce316882cceae8924030950d \
    file://dictionaries/sv/license;md5=fe5c586fe56d04fbdc44df187129ac29 \
    file://dictionaries/tr/readme.md;md5=27c07bbfa543cfdd163f2dad491703bd \
    file://dictionaries/uk/license;md5=7702f203b58979ebbc31bfaeb44f219c \
    file://dictionaries/vi/license;md5=8d9ecb4e7577ce760029786aba7ecec5 \
"

RDEPENDS:${PN} = "hunspell"

PV = "0.0.0+git"
SRCREV = "820a65e539e34a3a8c2a855d2450b84745c624ee"
SRC_URI = "git://github.com/wooorm/dictionaries.git;branch=main;protocol=https"

# Upstream repo does not tag
UPSTREAM_CHECK_COMMITS = "1"


do_install() {
    for LANGUAGE in `ls -d1 ${S}/dictionaries/*` ; do
        LANGUAGE_DIR=`basename $LANGUAGE | sed 's:-:_:'`
        install -D -m0644 $LANGUAGE/index.dic ${D}${datadir}/hunspell/$LANGUAGE_DIR.dic
        install -D -m0644 $LANGUAGE/index.aff ${D}${datadir}/hunspell/$LANGUAGE_DIR.aff
        install -D -m0644 $LANGUAGE/LICENSE   ${D}${datadir}/hunspell/LICENSE-$LANGUAGE_DIR 2>/dev/null || echo "No LICENSE for language $LANGUAGE"
    done
}

FILES:${PN} = "${datadir}/hunspell"
