package com.opensymphony.sitemesh.tagprocessor;

import com.opensymphony.sitemesh.tagprocessor.util.CharArray;

/**
 * Basic implementation of {@link TagRule}.
 *
 * @author Joe Walnes
 */
public abstract class BasicRule implements TagRule {

    private final String[] acceptableTagNames;

    protected TagProcessorContext context;

    protected BasicRule(String[] acceptableTagNames) {
        this.acceptableTagNames = acceptableTagNames;
    }

    protected BasicRule(String acceptableTagName) {
        this.acceptableTagNames = new String[] {acceptableTagName};
    }

    protected BasicRule() {
        this.acceptableTagNames = null;
    }

    @Override
    public abstract void process(Tag tag);

    @Override
    public void setContext(TagProcessorContext context) {
        this.context = context;
    }

    @Override
    public boolean shouldProcess(String name) {
        if (acceptableTagNames == null || acceptableTagNames.length < 1) {
            throw new UnsupportedOperationException(getClass().getName()
                    + " should be constructed with acceptableTagNames OR should implement shouldProcess()");
        }

        for (int i=0; i<acceptableTagNames.length; i++) {
            if (name.equals(acceptableTagNames[i])) return true;
        }
        return false;
    }

    protected CharArray currentBuffer() {
        return context.currentBuffer();
    }

}
