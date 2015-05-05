SUMMARY = "libsecret is a library for storing and retrieving passwords and other secrets"
LICENSE = "LGPLv2.1"
LIC_FILES_CHKSUM = "file://COPYING;md5=23c2a5e0106b99d75238986559bb5fc6"

inherit gnomebase gtk-doc

DEPENDS = "glib-2.0 libgcrypt"

GNOME_COMPRESS_TYPE = "xz"

EXTRA_OECONF += "--disable-manpages"

SRC_URI[archive.md5sum] = "279d723cd005e80d1d304f74a3488acc"
SRC_URI[archive.sha256sum] = "0c73aa762dbd1e38ba7b03de350e23ce818cb810b0784375e95ef61e004b02e3"

# Fails to build with thumb-1 (qemuarm)
# | {standard input}: Assembler messages:
# | {standard input}:686: Error: shifts in CMP/MOV instructions are only supported in unified syntax -- `mov r12,r12,ror#3'
# | {standard input}:686: Error: shifts in CMP/MOV instructions are only supported in unified syntax -- `mov r12,r12,ror#13'
# | {standard input}:687: Error: shifts in CMP/MOV instructions are only supported in unified syntax -- `mov r12,r12,ror#29'
# | {standard input}:687: Error: shifts in CMP/MOV instructions are only supported in unified syntax -- `mov r12,r12,ror#19'
# | {standard input}:688: Error: lo register required -- `orr r10,r10,r10'
ARM_INSTRUCTION_SET = "arm"
