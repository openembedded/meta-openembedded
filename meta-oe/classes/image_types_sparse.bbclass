inherit image_types

# This sets the granularity of the sparse image conversion. Chunk sizes will be
# specified in units of this value. Setting this value smaller than the
# underlying image's block size will not result in any further space saving.
# However, there is no loss in correctness if this value is larger or smaller
# than optimal. This value should be a power of two.
SPARSE_BLOCK_SIZE ??= "4096"

CONVERSIONTYPES += "sparse"

DELETE_RAWIMAGE_AFTER_SPARSE_CMD ??= "0"

CONVERSION_CMD:sparse = " \
    INPUT=${IMAGE_NAME}${IMAGE_NAME_SUFFIX}.${type} && \
    truncate --no-create --size=%${SPARSE_BLOCK_SIZE} $INPUT && \
    img2simg $INPUT $INPUT.sparse && \
    if [ ${DELETE_RAWIMAGE_AFTER_SPARSE_CMD} = 1 ]; then \
        rm -f $INPUT; \
        bbwarn 'Raw file $INPUT removed'; \
    fi \
"

CONVERSION_DEPENDS_sparse = "android-tools-native"
