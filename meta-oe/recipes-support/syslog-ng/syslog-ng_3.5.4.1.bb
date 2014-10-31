require syslog-ng.inc

SRC_URI += " \
    file://afsql-afsql_dd_insert_db-refactor.patch \
    file://deinit-the-new-config-when-reverting-to-the.patch \
    file://fix-a-memory-leak-in-log_driver_free.patch \
    file://fix-config-libnet.patch \
    file://fix-invalid-ownership.patch \
    file://Fix-the-memory-leak-problem-for-mutex.patch \
    file://Fix-the-memory-leak-problem-when-HAVE_ENVIRON-defined.patch \
    file://free-global-LogTemplateOptions.patch \
    file://still-free-the-unconsumed-item.patch \
    file://syslog-ng-verify-the-list-before-del.patch \
    file://configure.patch \
    file://dbifix.patch \
    file://rewrite-expr-grammar.ym-Free-up-token.patch \
    file://logwriter-dont-allocate-a-new-buffer.patch \
"

SRC_URI[md5sum] = "ff3bf223ebafbaa92b69a2d5b729f368"
SRC_URI[sha256sum] = "92c6969e4172b4fd32390f80043b4de7b116f29989d8c2e5a8a687ee6dcd6f66"
