package uk.ebi.embi.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class clazz, String uuid) {
        super(String.format("Entity of type [%s] with id [%s] not found", clazz.getSimpleName(), uuid));
    }
}
