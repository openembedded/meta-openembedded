require conf/machine/include/tune-arm926ejs.inc

# Increase this everytime you change something in the kernel
MACHINE_KERNEL_PR = "r50"

TARGET_ARCH = "arm"

KERNEL_IMAGETYPE = "uImage"

PREFERRED_PROVIDER_virtual/kernel = "linux-davinci"

PREFERRED_PROVIDER_virtual/bootloader = "u-boot"
UBOOT_MACHINE = "davinci_dvevm_config"

UBOOT_ENTRYPOINT = "0x80008000"
UBOOT_LOADADDRESS = "0x80008000"

EXTRA_IMAGEDEPENDS += "u-boot"

SERIAL_CONSOLE ?= "115200 ttyS0"
EXTRA_IMAGECMD_jffs2 = "--pad --little-endian --eraseblock=0x20000 -n"

#ROOT_FLASH_SIZE = "29"

MACHINE_FEATURES = "kernel26 serial ethernet usbhost usbgadget mmc alsa"
