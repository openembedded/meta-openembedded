SUMMARY = "Encode - character encodings"
DESCRIPTION = "The \"Encode\" module provides the interfaces between \
Perl's strings and the rest of the system.  Perl strings are sequences \
of characters."

AUTHOR = "Dan Kogai <dankogai+cpan@gmail.com>"
HOMEPAGE = "https://metacpan.org/release/Encode"
SECTION = "lib"
LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://META.json;md5=bb6a09a95259f80fbf2855c8f9dc3e85"

SRC_URI = "${CPAN_MIRROR}/authors/id/D/DA/DANKOGAI/Encode-${PV}.tar.gz"
SRC_URI[md5sum] = "90ee2160d40ade6cc4c4aa1b1ff4745e"
SRC_URI[sha256sum] = "0fa01359d34d1d129e9bcacb0b9bb821e36e9fb5f1404cfb557dfa680368f265"

S = "${WORKDIR}/Encode-${PV}"

inherit cpan

RDEPENDS_${PN} += " perl-module-bytes \
                    perl-module-constant \
                    perl-module-xsloader \
"

RPROVIDES_${PN} += "libencode-alias-perl \
                    libencode-byte-perl \
                    libencode-cjkconstants-perl \
                    libencode-cn-perl \
                    libencode-cn-hz-perl \
                    libencode-config-perl \
                    libencode-ebcdic-perl \
                    libencode-encoder-perl \
                    libencode-encoding-perl \
                    libencode-gsm0338-perl \
                    libencode-guess-perl \
                    libencode-jp-perl \
                    libencode-jp-h2z-perl \
                    libencode-jp-jis7-perl \
                    libencode-kr-perl \
                    libencode-kr-2022_kr-perl \
                    libencode-mime-header-perl \
                    libencode-mime-name-perl \
                    libencode-symbol-perl \
                    libencode-tw-perl \
                    libencode-unicode--perl \
                    libencode-unicode-utf7-perl \
                    libencoding-perl \
                    libencode-internal-perl \
                    libencode-mime-header-iso_2022_jp-perl \
                    libencode-utf8-perl \
                    libencode-utf_ebcdic-perl \
                    "

BBCLASSEXTEND = "native"
