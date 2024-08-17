package s1014ftjavaangular.userservice.domain.model.exception;

public class ResourceAlreadyExists extends RuntimeException{
    public ResourceAlreadyExists (String message){
        super(message);
    }
}
