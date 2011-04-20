require syslog-ng.inc
PR = "${INC_PR}.1"

SRC_URI += " \
  file://syslog-ng.conf \
  file://initscript \
"

SRC_URI[md5sum] = "ed8ebe559d52a63fb61e3e2db566643f"
SRC_URI[sha256sum] = "fa5abd4d99acee8fff8217061fb2407698a5bc89804d69f3ae97bffc72fcce48"
