package tobyspring.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {

        // 방법 1
        List<String> autoConfigs = new ArrayList<>();
        for (String candidate : ImportCandidates.load(MyAutoConfiguration.class, classLoader)) {
            autoConfigs.add(candidate);
        }

        // 방법 2
        // ImportCandidates.load(MyAutoConfiguration.class, classLoader).forEach(autoConfigs::add);

        return autoConfigs.toArray(new String[0]);
        // autoConfigs.toArray(String[]::new);
        //  Arrays.copyOf(autoConfigs.toArray(), autoConfigs.size(), String[].class);

        // 방법 3
        // Iterable<String> candidates = ImportCandidates.load(MyAutoConfiguration.class, classLoader);
        // return StreamSupport.stream(candidates.spliterator(), false).toArray(String[]::new);
    }
}
