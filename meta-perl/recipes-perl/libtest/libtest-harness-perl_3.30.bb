SUMMARY = "Test::Harness - Run Perl standard test scripts with statistics"
DESCRIPTION = "Although, for historical reasons, the Test::Harness \
distribution takes its name from this module it now exists only to provide \
TAP::Harness with an interface that is somewhat backwards compatible \
with Test::Harness 2.xx. If you're writing new code consider using \
TAP::Harness directly instead. \
\ 
Emulation is provided for runtests and execute_tests but the \
pluggable 'Straps' interface that previous versions of Test::Harness \
supported is not reproduced here. Straps is now available as a stand \
alone module: Test::Harness::Straps. \
\
See TAP::Parser, TAP::Harness for the main documentation for this \
distribution."

SECTION = "libs"

HOMEPAGE = "http://testanything.org"

LICENSE = "Artistic-1.0 | GPL-1.0+"
LIC_FILES_CHKSUM = "file://README;beginline=29;endline=30;md5=b08db4360eec119e875dddd7cb8a5ddd"

SRC_URI = "${CPAN_MIRROR}/authors/id/L/LE/LEONT/Test-Harness-${PV}.tar.gz"
SRC_URI[md5sum] = "c1ff25da5dfdf77adea13dc3465638dd"
SRC_URI[sha256sum] = "ff1900f3b3e61321d3c4b3283298f3106d43d55446605e9cfcf1dcec036acec1"

S = "${WORKDIR}/Test-Harness-${PV}"

inherit cpan

RPROVIDES_${PN} += "libapp-prove-perl \
                    libapp-prove-state-perl \
                    libapp-prove-state-result-perl \
                    libapp-prove-state-result-test-perl \
                    libtap-base-perl \
                    libtap-formatter-base-perl \
                    libtap-formatter-color-perl \
                    libtap-formatter-console-perl \
                    libtap-formatter-console-parallelsession-perl \
                    libtap-formatter-console-session-perl \
                    libtap-formatter-file-perl \
                    libtap-formatter-file-session-perl \
                    libtap-formatter-session-perl \
                    libtap-harness-perl \
                    libtap-harness-env-perl \
                    libtap-object-perl \
                    libtap-parser-perl \
                    libtap-parser-aggregator-perl \
                    libtap-parser-grammar-perl \
                    libtap-parser-iterator-perl \
                    libtap-parser-iterator-array-perl \
                    libtap-parser-iterator-process-perl \
                    libtap-parser-iterator-stream-perl \
                    libtap-parser-iteratorfactory-perl \
                    libtap-parser-multiplexer-perl \
                    libtap-parser-result-perl \
                    libtap-parsser-result-bailout-perl \
                    libtap-parser-result-comment-perl \
                    libtap-parser-result-plan-perl \
                    libtap-parser-result-pragma-perl \
                    libtap-parser-result-test-perl \
                    libtap-parser-result-unknown-perl \
                    libtap-parser-result-version-perl \
                    libtap-parser-result-yaml-perl \
                    libtap-parser-resultfactory-perl \
                    libtap-parser-scheduler-perl \
                    libtap-parser-scheduler-job-perl \
                    libtap-parser-scheduler-spinner-perl \
                    libtap-parser-source-perl \
                    libtap-parser-sourcehandler-perl \
                    libtap-parser-sourcehandler-executable-perl \
                    libtap-parser-sourcehandler-file-perl \
                    libtap-parser-sourcehandler-handle-perl \
                    libtap-parser-sourcehandler-perl-perl \
                    libtap-parser-sourcehandler-rawtap-perl \
                    libtap-parser-yamlish-reader-perl \
                    libtap-parser-yamlish-writer-perl \
                    "

BBCLASSEXTEND = "native"
