SUMMARY = "An unobtrusive argparse wrapper with natural syntax"
DESCRIPTION = "Building a command-line interface? Found yourself uttering \
'argh!' while struggling with the API of argparse? Don't like the complexity \
but need the power? \
\
Everything should be made as simple as possible, but no simpler. \
\
—Albert Einstein (probably) \
\
Argh is a smart wrapper for argparse. Argparse is a very powerful \
tool; Argh just makes it easy to use."

LICENSE = "LGPL-3.0-or-later"
LIC_FILES_CHKSUM = "file://COPYING.LESSER;md5=3000208d539ec061b899bce1d9ce9404 \
                    file://README.rst;beginline=261;endline=275;md5=39ec83a704aed9f33618c5d04e478a08 \
                    "

SRC_URI[sha256sum] = "9fca8e69c4dad4f8d2a7bdfbdd80b0754462b53510a03e1b5cf2b43a87e6e960"

inherit pypi python_flit_core

RDEPENDS:${PN} += " \
    python3-argcomplete \
    python3-logging \
"

BBCLASSEXTEND = "native nativesdk"
