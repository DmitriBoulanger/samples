package de.dbo.samples.javafx.spring2.ui;

import static de.dbo.tools.utils.print.Profiler.elapsed;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

import de.dbo.samples.javafx.spring2.entity.Contact;
import de.dbo.samples.javafx.spring2.service.ContactService;

import java.util.List;

/**
 * 
 * @author Dmitri Boulanger, Hombach
 *
 * D. Knuth: Programs are meant to be read by humans and 
 *           only incidentally for computers to execute 
 *
 */

public class Controller {
    private static final Logger log = LoggerFactory.getLogger(Controller.class);

    @Autowired 
    private ContactService contactService;

    // JavaFX Injection
    
    @FXML private TableView<Contact> table;
    
    @FXML private TextField txtName;
    @FXML private TextField txtPhone;
    @FXML private TextField txtEmail;

    // Variables
    private ObservableList<Contact> data;

    /**
     * Initialization of the Java FX Controller
     * Метод вызывается после того как FXML загрузчик произвел инъекции полей.
     *
     * Обратите внимание, что имя метода <b>обязательно</b> должно быть "initialize",
     * в противном случае, метод не вызовется.
     *
     * Также на этом этапе еще отсутствуют бины спринга
     * и для инициализации лучше использовать метод,
     * описанный аннотацией @PostConstruct,
     * который вызовется спрингом, после того, как им будут произведены все инъекции.
     * 
     * {@link Controller#init()}
     */
    @FXML
    public void initialize() {
	log.info("initialize void ...");
    }

    /**
     * All injections are done before this method is called
     * This method performs the actual controller initialization
     */
    @SuppressWarnings("unchecked")
    @PostConstruct
    public void init() {
	log.debug("post-constuct init .....");
	final long start = System.currentTimeMillis();
	
        final List<Contact> contacts = contactService.findAll();
        data = FXCollections.observableArrayList(contacts);

        // Table columns
        final TableColumn<Contact, String> idColumn = new TableColumn<>("ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        final TableColumn<Contact, String> nameColumn = new TableColumn<>("Имя");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        final TableColumn<Contact, String> phoneColumn = new TableColumn<>("Телефон");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        final TableColumn<Contact, String> emailColumn = new TableColumn<>("E-mail");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        table.getColumns().setAll(idColumn, nameColumn, phoneColumn, emailColumn);

        // Table data
        table.setItems(data);
        
        log.info("post-constuct init done. " + elapsed(start));
    }

    /**
     * Метод, вызываемый при нажатии на кнопку "Добавить".
     * Привязан к кнопке в FXML файле представления.
     */
    @FXML
    public void addContact() {
	log.info("add contact .....");
	
        final Contact contact = new Contact(txtName.getText(), txtPhone.getText(), txtEmail.getText());
        contactService.save(contact);
        data.add(contact);

        // clean fields
        txtName.setText("");
        txtPhone.setText("");
        txtEmail.setText("");
        
        log.info("add contact done.");
    }
}
