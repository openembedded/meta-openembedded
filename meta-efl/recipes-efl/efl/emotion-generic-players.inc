DESCRIPTION = "Emotion generic players"
LICENSE = "GPLv2"
LIC_FILES_CHKSUM = "file://COPYING;md5=69f44058245ce5c596d56bb622d5dabd"

inherit efl

SRCNAME = "emotion_generic_players"

# creates empty packages until you get vlc-2 and enable it here
PACKAGECONFIG ?= ""
# needs eina and ecore from efl and vlc-2 (currently we have 1.11)
PACKAGECONFIG[vlc] = "--with-vlc,--without-vlc,vlc efl"
