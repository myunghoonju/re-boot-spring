package reboot.spring.config;

import org.springframework.boot.context.annotation.ImportCandidates;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.ArrayList;
import java.util.List;

public class MyAutoConfigImportSelector implements DeferredImportSelector {

    private final ClassLoader classLoader;

    public MyAutoConfigImportSelector(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> objects = new ArrayList<>();
        Iterable<String> importCandidates = ImportCandidates.load(MyAutoConfig.class, classLoader);
        importCandidates.forEach(objects::add);

        return objects.toArray(String[]::new);
    }
}