package test;

import hudson.Extension;
import hudson.model.RootAction;
import org.kohsuke.stapler.HttpResponse;
import org.kohsuke.stapler.HttpResponses;

@Extension
public class SampleRootAction implements RootAction {
    @Override
    public String getUrlName() {
        return "sample";
    }

    @Override
    public String getIconFileName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return null;
    }

    public HttpResponse doIndex() {
        return HttpResponses.plainText("sample served");
    }
}
