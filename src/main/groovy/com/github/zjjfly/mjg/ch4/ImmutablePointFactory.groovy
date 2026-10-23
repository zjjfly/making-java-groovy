package com.github.zjjfly.mjg.ch4

/**
 * @author <a href="https://github.com/zjjfly"/>zjjfly</a>
 */
@Singleton
class ImmutablePointFactory {
    ImmutablePoint newImmutablePoint(xval,yval) {
        return new ImmutablePoint(x:xval,y:yval)
    }
}
