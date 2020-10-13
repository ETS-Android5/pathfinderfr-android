package org.pathfinderfr.app.database.entity;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.annotation.NonNull;

import org.pathfinderfr.app.database.DBHelper;
import org.pathfinderfr.app.util.ConfigurationUtil;
import org.pathfinderfr.app.util.StringUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ClassFeatureFactory extends DBEntityFactory {

    public static final String FACTORY_ID        = "CLASSFEATURES";

    private static final String TABLENAME         = "classfeatures";
    private static final String COLUMN_CLASS      = "class";
    private static final String COLUMN_ARCHETYPE  = "archetype";
    private static final String COLUMN_CONDITIONS = "conditions";
    private static final String COLUMN_AUTOMATIC  = "auto";
    private static final String COLUMN_LEVEL      = "level";

    private static final String YAML_NAME         = "Nom";
    private static final String YAML_DESC         = "Description";
    private static final String YAML_DESC_HTML    = "DescriptionHTML";
    private static final String YAML_REFERENCE    = "Référence";
    private static final String YAML_SOURCE       = "Source";
    private static final String YAML_LINKED_TO    = "Lié à";
    private static final String YAML_CLASS        = "Classe";
    private static final String YAML_ARCHETYPE    = "Archétype";
    private static final String YAML_CONDITIONS   = "Conditions";
    private static final String YAML_AUTO         = "Auto";
    private static final String YAML_LEVEL        = "Niveau";

    private static ClassFeatureFactory instance;

    private Map<Long, Class> classesById;
    private Map<String, Class> classesByName;
    private Map<Long, ClassArchetype> archetypesById;
    private Map<String, ClassArchetype> archetypesByName;

    private ClassFeatureFactory() {
        classesById = new HashMap<>();
        classesByName = new HashMap<>();
        archetypesById = new HashMap<>();
        archetypesByName = new HashMap<>();
    }

    @Override
    public String getColumnSource() {
        return super.getColumnSource();
    }

    @Override
    public void cleanup() {
        super.cleanup();
        classesById.clear();
        classesByName.clear();
        archetypesById.clear();
        archetypesByName.clear();
    }

    /**
     * @return then unique instance of that factory
     */
    public static synchronized ClassFeatureFactory getInstance() {
        if (instance == null) {
            instance = new ClassFeatureFactory();
        }
        return instance;
    }

    private synchronized void loadClasses() {
        classesById.clear();
        classesByName.clear();
        List<DBEntity> fullList = DBHelper.getInstance(null).getAllEntities(ClassFactory.getInstance(),-1);
        for(DBEntity e : fullList) {
            classesById.put(e.getId(), (Class)e);
            classesByName.put(e.getName(), (Class)e);
        }
    }

    private synchronized Class getClass(String name) {
        if(classesByName.size() == 0) { loadClasses(); }
        return classesByName.get(name);
    }

    private synchronized Class getClass(long id) {
        if(classesById.size() == 0) { loadClasses(); }
        return classesById.get(id);
    }

    private synchronized void loadArchetypes() {
        archetypesById.clear();
        archetypesByName.clear();
        List<DBEntity> fullList = DBHelper.getInstance(null).getAllEntities(ClassArchetypesFactory.getInstance());
        for(DBEntity e : fullList) {
            archetypesById.put(e.getId(), (ClassArchetype)e);
            archetypesByName.put(e.getName(), (ClassArchetype)e);
        }
    }

    private synchronized ClassArchetype getArchetype(String name) {
        if(archetypesByName.size() == 0) { loadArchetypes(); }
        return archetypesByName.get(name);
    }

    private synchronized ClassArchetype getArchetype(long id) {
        if(archetypesById.size() == 0) { loadArchetypes(); }
        return archetypesById.get(id);
    }

    @Override
    public String getFactoryId() {
        return FACTORY_ID;
    }

    @Override
    public String getTableName() {
        return TABLENAME;
    }

    @Override
    public String getQueryCreateTable() {
        String query = String.format( "CREATE TABLE IF NOT EXISTS %s (" +
                        "%s integer PRIMARY key, " +
                        "%s integer version, " +
                        "%s text, %s text, %s text, %s text," +
                        "%s integer, %s integer, %s text, %s integer, %s integer" +
                        ")",
                TABLENAME, COLUMN_ID, COLUMN_VERSION,
                COLUMN_NAME, COLUMN_DESC, COLUMN_REFERENCE, COLUMN_SOURCE,
                COLUMN_CLASS, COLUMN_ARCHETYPE, COLUMN_CONDITIONS, COLUMN_LEVEL, COLUMN_AUTOMATIC);
        return query;
    }

    /**
     * @return SQL statement for upgrading DB from v15 to v16
     */
    public String getQueryUpgradeV16() {
        return String.format("ALTER TABLE %s ADD COLUMN %s integer;", getTableName(), COLUMN_ARCHETYPE);
    }

    /**
     * @return the query to fetch all entities (including fields required for filtering)
     */
    @Override
    public String getQueryFetchAll(Integer version, String... sources) {
        return String.format("SELECT %s,%s,%s,%s,%s,%s FROM %s %s ORDER BY %s COLLATE UNICODE",
                COLUMN_ID, COLUMN_NAME, COLUMN_CLASS, COLUMN_ARCHETYPE, COLUMN_LEVEL, COLUMN_AUTOMATIC, getTableName(), getFilters(version, sources), COLUMN_NAME);
    }


    @Override
    public ContentValues generateContentValuesFromEntity(@NonNull DBEntity entity) {
        if (!(entity instanceof ClassFeature)) {
            return null;
        }
        ClassFeature classFeature = (ClassFeature) entity;
        ContentValues contentValues = new ContentValues();
        contentValues.put(ClassArchetypesFactory.COLUMN_VERSION, classFeature.getVersion());
        contentValues.put(ClassFeatureFactory.COLUMN_NAME, classFeature.getName());
        contentValues.put(ClassFeatureFactory.COLUMN_DESC, classFeature.getDescription());
        contentValues.put(ClassFeatureFactory.COLUMN_REFERENCE, classFeature.getReference());
        contentValues.put(ClassFeatureFactory.COLUMN_SOURCE, classFeature.getSource());
        contentValues.put(ClassFeatureFactory.COLUMN_CLASS, classFeature.getClass_().getId());
        contentValues.put(ClassFeatureFactory.COLUMN_ARCHETYPE, (classFeature.getClassArchetype() == null ? null : classFeature.getClassArchetype().getId()));
        contentValues.put(ClassFeatureFactory.COLUMN_CONDITIONS, classFeature.getConditions());
        contentValues.put(ClassFeatureFactory.COLUMN_LEVEL, classFeature.getLevel());
        contentValues.put(ClassFeatureFactory.COLUMN_AUTOMATIC, classFeature.isAuto() ? 1 : 0);
        return contentValues;
    }


    @Override
    public DBEntity generateEntity(@NonNull Cursor resource) {
        ClassFeature classFeature = new ClassFeature();
        classFeature.setId(resource.getLong(resource.getColumnIndex(ClassFeatureFactory.COLUMN_ID)));
        classFeature.setVersion(extractValueAsInt(resource, ClassFeatureFactory.COLUMN_VERSION));
        classFeature.setName(extractValue(resource, ClassFeatureFactory.COLUMN_NAME));
        classFeature.setDescription(extractValue(resource, ClassFeatureFactory.COLUMN_DESC));
        classFeature.setReference(extractValue(resource, ClassFeatureFactory.COLUMN_REFERENCE));
        classFeature.setSource(extractValue(resource, ClassFeatureFactory.COLUMN_SOURCE));
        classFeature.setClass(getClass(extractValueAsInt(resource, ClassFeatureFactory.COLUMN_CLASS)));
        classFeature.setClassArchetype(getArchetype(extractValueAsInt(resource, ClassFeatureFactory.COLUMN_ARCHETYPE)));
        classFeature.setConditions(extractValue(resource, ClassFeatureFactory.COLUMN_CONDITIONS));
        classFeature.setLevel(extractValueAsInt(resource, ClassFeatureFactory.COLUMN_LEVEL));
        classFeature.setAuto(extractValueAsBoolean(resource, ClassFeatureFactory.COLUMN_AUTOMATIC));
        return classFeature;
    }

    @Override
    public DBEntity generateEntity(@NonNull Map<String, Object> attributes) {
        ClassFeature classFeature = new ClassFeature();
        classFeature.setName((String)attributes.get((String)YAML_NAME));
        classFeature.setDescription(extractDescription(attributes, YAML_DESC, YAML_DESC_HTML));
        classFeature.setReference((String)attributes.get(YAML_REFERENCE));
        classFeature.setSource((String)attributes.get(YAML_SOURCE));
        classFeature.setClass(getClass((String)attributes.get(YAML_CLASS)));
        classFeature.setClassArchetype(getArchetype((String)attributes.get(YAML_ARCHETYPE)));
        classFeature.setConditions((String)attributes.get(YAML_CONDITIONS));
        classFeature.setLevel(Integer.parseInt((String)attributes.get(YAML_LEVEL)));
        classFeature.setAuto("True".equalsIgnoreCase((String)attributes.get(YAML_AUTO)));
        return classFeature.isValid() ? classFeature : null;
    }

    @Override
    public String generateDetails(@NonNull DBEntity entity, @NonNull String templateList, @NonNull String templateItem) {
        return "";
    }

    @Override
    public String generateHTMLContent(@NonNull DBEntity entity) {
        if(!(entity instanceof ClassFeature)) {
            return "";
        }
        ClassFeature classFeature = (ClassFeature)entity;

        Properties cfg =  ConfigurationUtil.getInstance(null).getProperties();
        String templateItem = cfg.getProperty("template.item.prop");

        StringBuilder buf = new StringBuilder();
        String source = classFeature.getSource() == null ? null : getTranslatedText("source." + classFeature.getSource().toLowerCase());
        buf.append("<ul class=\"props\">");
        buf.append(generateItemDetail(templateItem, YAML_SOURCE, source));
        if(classFeature.getClass_() != null) buf.append(generateItemDetail(templateItem, YAML_CLASS, classFeature.getClass_().getName()));
        if(classFeature.getClassArchetype() != null) buf.append(generateItemDetail(templateItem, YAML_ARCHETYPE, classFeature.getClassArchetype().getName()));
        if(classFeature.getLinkedTo() != null) buf.append(generateItemDetail(templateItem, YAML_LINKED_TO, classFeature.getLinkedTo().getNameShort()));
        buf.append(generateItemDetail(templateItem, YAML_CONDITIONS, classFeature.getConditions()));
        buf.append(generateItemDetail(templateItem, YAML_LEVEL, String.valueOf(classFeature.getLevel())));
        buf.append("</ul>");
        buf.append(StringUtil.cleanText(classFeature.getDescription()));

        return buf.toString();
    }
}
