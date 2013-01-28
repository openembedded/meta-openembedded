require syslog-ng.inc
PR = "${INC_PR}.1"

SRC_URI += " \
  file://syslog-ng.conf \
  file://initscript \
"

SRC_URI[md5sum] = "60737452ce898f9dc7170dfdc9bfd732"
SRC_URI[sha256sum] = "ffc9f3a0ebea836c1c737b1ff49efe731d885af1d8aacf9eca79d9144eeefa89"
