package view.Image;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.SQLException;

import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.BindingContainer;

import oracle.jbo.Row;
import oracle.jbo.domain.BlobDomain;

import org.apache.myfaces.trinidad.model.UploadedFile;

public class UploadBeanCompany {
    public UploadBeanCompany() {
        super();
    }
    
    private UploadedFile _file;

    public UploadedFile getFile() {
        return _file;
    }

    public void setFile(UploadedFile file) {
        _file = file;
    }

    public String UploadImage() {

        UploadedFile myfile = (UploadedFile)this.getFile();
       
        BindingContext bindingctx = BindingContext.getCurrent();
        BindingContainer bindings = bindingctx.getCurrentBindingsEntry();
        DCBindingContainer bindingsImpl = (DCBindingContainer)bindings;
        DCIteratorBinding iter = bindingsImpl.findIteratorBinding("TblCompanyView2Iterator");
       
        Row row = iter.getCurrentRow();
        row.setAttribute("ImageName", createBlobDomain(myfile));
    
     return null;
    }

    private BlobDomain createBlobDomain(UploadedFile file) {

        InputStream in = null;
        BlobDomain blobDomain = null;
        OutputStream out = null;

        try {
            in = file.getInputStream();

            blobDomain = new BlobDomain();
            out = blobDomain.getBinaryOutputStream();
            byte[] buffer = new byte[8192];
            int bytesRead = 0;

            while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
                out.write(buffer, 0, bytesRead);
            }

            in.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.fillInStackTrace();
        }

        return blobDomain;
    }


  
}
