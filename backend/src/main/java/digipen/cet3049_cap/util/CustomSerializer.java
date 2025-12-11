package digipen.cet3049_cap.util;

import digipen.cet3049_cap.model.Employees;
import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

public class CustomSerializer extends ValueSerializer<Employees> {
    @Override
    public void serialize(Employees value, JsonGenerator gen, SerializationContext ctxt) throws JacksonException {
        if  (value != null) {
            gen.writeStartObject();
            gen.writeStringProperty("empNo", value.getEmpNo().toString());
            gen.writeStringProperty("birthDate",  value.getBirthDate().toString());
            gen.writeStringProperty("firstName", value.getFirstName());
            gen.writeStringProperty("lastName", value.getLastName());
            gen.writeStringProperty("gender", value.getGender().toString());
            gen.writeStringProperty("hireDate",  value.getHireDate().toString());
            gen.writeStringProperty("deptEmp", value.getDeptEmp().toString());
            gen.writeStringProperty("deptManager", value.getDeptManager().toString());
            gen.writeStringProperty("salaries", value.getSalaries().toString());
            gen.writeStringProperty("titles", value.getTitles().toString());
            gen.writeEndObject();
        }
    }

}
