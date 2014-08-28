SUMMARY = "Automatically give your module the ability to have plugins"
DESCRIPTION = "Provides a simple but, hopefully, extensible way of \
having 'plugins' for your module. Obviously this isn't going to be the \
be all and end all of solutions but it works for me.\
\
Essentially all it does is export a method into your namespace that \
looks through a search path for .pm files and turn those into class \
names.\
\
Optionally it instantiates those classes for you."
SECTION = "libs"

AUTHOR = "Simon Wistow <simon@thegestalt.org>"
HOMEPAGE = "https://github.com/simonwistow/Module-Pluggable"

LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://README;beginline=322;endline=325;md5=086450ce010f6fda25db0b38fcc41086"

SRCNAME = "Module-Pluggable"
SRC_URI = "${CPAN_MIRROR}/authors/id/S/SI/SIMONW/${SRCNAME}-${PV}.tar.gz"
SRC_URI[md5sum] = "1b71ed7a67ad8c048d1499540bc892ba"
SRC_URI[sha256sum] = "e2dc354043bb16f1f3df8c4bb26070b26e594819f218cf8b8ac19e79c720916f"

S = "${WORKDIR}/${SRCNAME}-${PV}"

inherit cpan_build

RDEPENDS_${PN} = " perl-module-base \
		   perl-module-deprecate \
                   perl-module-file-basename \
		   perl-module-file-find \
                   perl-module-file-spec \
		   perl-module-file-spec-functions \
		   perl-module-if \
                   perl-module-test-more \
"

BBCLASSEXTEND = "native"

