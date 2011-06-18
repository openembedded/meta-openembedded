require libsdl-1.2.14.inc

DEPENDS += "virtual/libgl virtual/libx11 libxext tslib"
DEPENDS_avr32 += "alsa-lib virtual/libx11 libxext tslib"

PR = "${INC_PR}.0"

EXTRA_OECONF = " \
  --disable-static --disable-debug --enable-cdrom --enable-threads --enable-timers --enable-endian \
  --enable-file --enable-oss --enable-alsa --disable-esd --disable-arts \
  --disable-diskaudio --disable-nas --disable-esd-shared --disable-esdtest \
  --disable-mintaudio --disable-nasm --enable-video-x11 --disable-video-dga \
  --enable-video-fbcon --disable-video-directfb --disable-video-ps2gs \
  --disable-video-xbios --disable-video-gem --disable-video-dummy \
  --enable-video-opengl --enable-input-events --enable-pthreads \
  --disable-video-picogui --disable-video-qtopia --enable-dlopen \
  --enable-input-tslib --disable-video-ps3 --disable-rpath \
"
