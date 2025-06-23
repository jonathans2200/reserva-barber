package proyectogalaxy.exeptions;

public class ApiError {

    private int status;
    private String mensaje;
    private String error;

    public ApiError(int status, String mensaje, String error) {
        this.status = status;
        this.mensaje = mensaje;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
