include ../../rules_yocto.mk
NAME = adbd

SOURCES = \
	adb/daemon/main.cpp \
	adb/daemon/auth.cpp \
	adb/daemon/jdwp_service.cpp \
	adb/daemon/file_sync_service.cpp \
	adb/daemon/services.cpp \
	adb/daemon/shell_service.cpp \
	adb/daemon/remount_service.cpp \
	adb/daemon/restart_service.cpp \
	adb/daemon/reboot_service.cpp \
	adb/daemon/framebuffer_service.cpp \
	adb/daemon/set_verity_enable_state_service.cpp \
	adb/shell_service_protocol.cpp \
	adb/adb.cpp \
	adb/adb_io.cpp \
	adb/adb_listeners.cpp \
	adb/adb_trace.cpp \
	adb/adb_unique_fd.cpp \
	adb/adb_utils.cpp \
	adb/fdevent.cpp \
	adb/services.cpp \
	adb/sockets.cpp \
	adb/socket_spec.cpp \
	adb/sysdeps/errno.cpp \
	adb/transport.cpp \
	adb/transport_fd.cpp \
	adb/transport_local.cpp \
	adb/transport_usb.cpp \
	adb/sysdeps_unix.cpp \
	adb/sysdeps/posix/network.cpp \
	adb/daemon/usb_legacy.cpp \
	adb/daemon/usb_ffs.cpp \
	adb/daemon/usb.cpp \
	diagnose_usb/diagnose_usb.cpp \
	libasyncio/AsyncIO.cpp \

CXXFLAGS += -std=gnu++20
CPPFLAGS += -Iinclude -Iadb -Ibase/include  -I$(OUT_DIR)/usr/include/ -Imkbootimg/include/bootimg -Ifs_mgr/include \
	    -Ifs_mgr/include_fstab \
            -DADB_VERSION='"$(DEB_VERSION)"' -D_GNU_SOURCE
LDFLAGS += -Wl,-rpath=/usr/lib/$(DEB_HOST_MULTIARCH)/android -Wl,-rpath-link=$(OUT_DIR)/usr/lib/$(DEB_HOST_MULTIARCH)/android/ \
           -lpthread -L$(OUT_DIR)/usr/lib/$(DEB_HOST_MULTIARCH)/android/ -lbase -lcrypto_utils -lcrypto -lcutils -llog -lresolv

PAGE_SIZE ?= 4096

CXXFLAGS += -UADB_HOST
CXXFLAGS += -DADB_HOST=0
CXXFLAGS += -DALLOW_ADBD_DISABLE_VERITY
CXXFLAGS += -DALLOW_ADBD_NO_AUTH
CXXFLAGS += -DPLATFORM_TOOLS_VERSION='"28.0.2"'
CXXFLAGS += -Idiagnose_usb/include
CXXFLAGS += -Iadb/daemon/include
CXXFLAGS += -Ilibasyncio/include
CXXFLAGS += -Wno-c++11-narrowing
CXXFLAGS += -DPAGE_SIZE=$(PAGE_SIZE)


# -latomic should be the last library specified
# https://github.com/android/ndk/issues/589
ifneq ($(filter armel mipsel,$(DEB_HOST_ARCH)),)
  LDFLAGS += -latomic
endif

build: $(SOURCES)
	mkdir --parents ../../../android-tools/adbd
	$(CXX) $^ -o ../../../android-tools/adbd/adbd $(CXXFLAGS) $(CPPFLAGS) $(LDFLAGS)

clean:
	$(RM) $(OUT_DIR)/usr/bin/$(NAME)
