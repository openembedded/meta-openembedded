# Pull Request Summary

## 🎯 **Fix: python3-posix-ipc - Add librt linking to enable message queue support**

### **Problem**
The `python3-posix-ipc` package was being built without message queue support, causing the `MessageQueue` class to be unavailable at runtime. This was due to missing linkage with the real-time library (`librt`) during cross-compilation.

### **Solution**
Added `LDFLAGS += "-lrt"` to the BitBake recipe to ensure proper linking with the real-time library during the build process.

### **Changes Made**
- **File**: `meta-python/recipes-devtools/python/python3-posix-ipc_1.2.0.bb`
- **Addition**: Added `LDFLAGS += "-lrt"` with explanatory comment

### **Testing**
- ✅ Manual build test confirms MessageQueue class is now available
- ✅ Semaphore and shared memory functionality remains intact
- ✅ Recipe syntax is valid and follows meta-openembedded conventions

### **Impact**
- Enables full POSIX IPC functionality in `python3-posix-ipc`
- Fixes GitHub issue #916
- Maintains backward compatibility with existing functionality

### **Technical Details**
The fix ensures that during cross-compilation, the posix_ipc module's build system can properly detect and link against the real-time library, enabling the compilation of message queue support that was previously being excluded.

---

**This PR is ready for review and should resolve the reported issue with missing MessageQueue support in python3-posix-ipc.**
