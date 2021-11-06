package io.nirahtech.virtualization.manager.domain.entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public final class SystemInfo implements Serializable {

    private static final class Bios implements Serializable {
        private String vendor;
        private String version;
        private String date;
        private String release;

        public String getVendor() {
            return this.vendor;
        }

        public String getVersion() {
            return this.version;
        }

        public String getDate() {
            return this.date;
        }

        public String getRelease() {
            return this.release;
        }

    }

    private static final class System implements Serializable {
        private String manufacturer;
        private String product;
        private String version;
        private String serial;
        private String uuid;
        private String sku;
        private String family;

        public String getFamily() {
            return this.family;
        }

        public String getManufacturer() {
            return this.manufacturer;
        }

        public String getProduct() {
            return this.product;
        }

        public String getSerial() {
            return this.serial;
        }

        public String getSku() {
            return this.sku;
        }

        public String getUuid() {
            return this.uuid;
        }

        public String getVersion() {
            return this.version;
        }
    }

    private static final class BaseBoard implements Serializable {
        private String manufacturer;
        private String product;
        private String version;
        private String serial;
        private String asset;
        private String location;

        public String getAsset() {
            return this.asset;
        }

        public String getLocation() {
            return this.location;
        }

        public String getManufacturer() {
            return this.manufacturer;
        }

        public String getProduct() {
            return this.product;
        }

        public String getSerial() {
            return this.serial;
        }

        public String getVersion() {
            return this.version;
        }
    }

    private static final class Chassis implements Serializable {
        private String manufacturer;
        private String product;
        private String version;
        private String serial;
        private String asset;
        private String sku;

        public String getAsset() {
            return this.asset;
        }

        public String getManufacturer() {
            return this.manufacturer;
        }

        public String getProduct() {
            return this.product;
        }

        public String getSerial() {
            return this.serial;
        }

        public String getSku() {
            return this.sku;
        }

        public String getVersion() {
            return this.version;
        }
    }

    private static final class Processor implements Serializable {
        private String socketDestination;
        private String type;
        private String family;
        private String manufacturer;
        private String signature;
        private String version;
        private String externalClock;
        private String maxSpeed;
        private String status;
        private String serialNumber;
        private String partNumber;

        public String getExternalClock() {
            return this.externalClock;
        }

        public String getFamily() {
            return this.family;
        }

        public String getManufacturer() {
            return this.manufacturer;
        }

        public String getMaxSpeed() {
            return this.maxSpeed;
        }

        public String getPartNumber() {
            return this.partNumber;
        }

        public String getSerialNumber() {
            return this.serialNumber;
        }

        public String getSignature() {
            return this.signature;
        }

        public String getSocketDestination() {
            return this.socketDestination;
        }

        public String getStatus() {
            return this.status;
        }

        public String getType() {
            return this.type;
        }

        public String getVersion() {
            return this.version;
        }
    }

    private static final class MemoryDevice implements Serializable {
        private String size;
        private String formFactor;
        private String locator;
        private String bankLocator;
        private String type;
        private String typeDetail;
        private String speed;
        private String manufacturer;
        private String serialNumber;
        private String partNumber;

        public String getBankLocator() {
            return this.bankLocator;
        }

        public String getFormFactor() {
            return this.formFactor;
        }

        public String getLocator() {
            return this.locator;
        }

        public String getManufacturer() {
            return this.manufacturer;
        }

        public String getPartNumber() {
            return this.partNumber;
        }

        public String getSerialNumber() {
            return this.serialNumber;
        }

        public String getSize() {
            return this.size;
        }

        public String getSpeed() {
            return this.speed;
        }

        public String getType() {
            return this.type;
        }

        public String getTypeDetail() {
            return this.typeDetail;
        }
    }

    private static final class MapSystemInfoObjectHandlerSax extends DefaultHandler {
        private StringBuilder currentValue = new StringBuilder();
        SystemInfo result = null;
        boolean isProcessingBios = false;
        boolean isProcessingSystem = false;
        boolean isProcessingBaseBoard = false;
        boolean isProcessingChassis = false;
        boolean isProcessingProcessor = false;
        boolean isProcessingMemory = false;
        MemoryDevice currentMemoryDevice = null;

        public SystemInfo getResult() {
            return result;
        }

        @Override
        public void startDocument() {
            result = null;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) {

            // reset the tag value
            currentValue.setLength(0);

            // start of loop
            if (qName.equalsIgnoreCase("sysinfo")) {
                result = new SystemInfo();
                result.type = attributes.getValue("type");
            } else if (qName.equalsIgnoreCase("bios")) {
                isProcessingBios = true;
                isProcessingSystem = false;
                isProcessingBaseBoard = false;
                isProcessingChassis = false;
                isProcessingProcessor = false;
                isProcessingMemory = false;
                if (result != null) {
                    result.bios = new Bios();
                }
            } else if (qName.equalsIgnoreCase("system")) {
                isProcessingBios = false;
                isProcessingSystem = true;
                isProcessingBaseBoard = false;
                isProcessingChassis = false;
                isProcessingProcessor = false;
                isProcessingMemory = false;
                if (result != null) {
                    result.system = new System();
                }
            } else if (qName.equalsIgnoreCase("baseBoard")) {
                isProcessingBios = false;
                isProcessingSystem = false;
                isProcessingBaseBoard = true;
                isProcessingChassis = false;
                isProcessingProcessor = false;
                isProcessingMemory = false;
                if (result != null) {
                    result.baseBoard= new BaseBoard();
                }
            } else if (qName.equalsIgnoreCase("chassis")) {
                isProcessingBios = false;
                isProcessingSystem = false;
                isProcessingBaseBoard = false;
                isProcessingChassis = true;
                isProcessingProcessor = false;
                isProcessingMemory = false;
                if (result != null) {
                    result.chassis= new Chassis();
                }
            } else if (qName.equalsIgnoreCase("processor")) {
                isProcessingBios = false;
                isProcessingSystem = false;
                isProcessingBaseBoard = false;
                isProcessingChassis = false;
                isProcessingProcessor = true;
                isProcessingMemory = false;
                if (result != null) {
                    result.processor= new Processor();
                }
            } else if (qName.equalsIgnoreCase("memory_device")) {
                isProcessingBios = false;
                isProcessingSystem = false;
                isProcessingBaseBoard = false;
                isProcessingChassis = false;
                isProcessingProcessor = false;
                isProcessingMemory = true;
                if (result != null) {
                    result.memoryDevices = new HashSet();
                    currentMemoryDevice = new MemoryDevice();
                }
            } else if (qName.equalsIgnoreCase("entry")) {
                if (isProcessingBios) {
                    if (attributes.getValue("vendor") != null) {
                        if (result != null && result.bios != null) {
                            result.bios.vendor = attributes.getValue("vendor");
                        }
                    } else if (attributes.getValue("version") != null) {
                        if (result != null && result.bios != null) {
                            result.bios.version = attributes.getValue("version");
                        }
                    } else if (attributes.getValue("date") != null) {
                        if (result != null && result.bios != null) {
                            result.bios.date = attributes.getValue("date");
                        }
                    } else if (attributes.getValue("release") != null) {
                        if (result != null && result.bios != null) {
                            result.bios.release = attributes.getValue("release");
                        }
                    } 
                } else if (isProcessingSystem) {
                    if (attributes.getValue("manufacturer") != null) {
                        if (result != null && result.system != null) {
                            result.system.manufacturer = attributes.getValue("manufacturer");
                        }
                    } else if (attributes.getValue("product") != null) {
                        if (result != null && result.system != null) {
                            result.system.product = attributes.getValue("product");
                        }
                    } else if (attributes.getValue("version") != null) {
                        if (result != null && result.system != null) {
                            result.system.version = attributes.getValue("version");
                        }
                    } else if (attributes.getValue("serial") != null) {
                        if (result != null && result.system != null) {
                            result.system.serial = attributes.getValue("serial");
                        }
                    } else if (attributes.getValue("uuid") != null) {
                        if (result != null && result.system != null) {
                            result.system.uuid = attributes.getValue("uuid");
                        }
                    } else if (attributes.getValue("sku") != null) {
                        if (result != null && result.system != null) {
                            result.system.sku = attributes.getValue("sku");
                        }
                    } else if (attributes.getValue("family") != null) {
                        if (result != null && result.system != null) {
                            result.system.family = attributes.getValue("family");
                        }
                    } 
                } else if (isProcessingBaseBoard) {
                    if (attributes.getValue("manufacturer") != null) {
                        if (result != null && result.baseBoard != null) {
                            result.baseBoard.manufacturer = attributes.getValue("manufacturer");
                        }
                    } else if (attributes.getValue("product") != null) {
                        if (result != null && result.baseBoard != null) {
                            result.baseBoard.product = attributes.getValue("product");
                        }
                    } else if (attributes.getValue("version") != null) {
                        if (result != null && result.baseBoard != null) {
                            result.baseBoard.version = attributes.getValue("version");
                        }
                    } else if (attributes.getValue("serial") != null) {
                        if (result != null && result.baseBoard != null) {
                            result.baseBoard.serial = attributes.getValue("serial");
                        }
                    } else if (attributes.getValue("asset") != null) {
                        if (result != null && result.baseBoard != null) {
                            result.baseBoard.asset = attributes.getValue("asset");
                        }
                    } else if (attributes.getValue("location") != null) {
                        if (result != null && result.baseBoard != null) {
                            result.baseBoard.location = attributes.getValue("location");
                        }
                    }
                } else if (isProcessingChassis) {
                    if (attributes.getValue("manufacturer") != null) {
                        if (result != null && result.chassis != null) {
                            result.chassis.manufacturer = attributes.getValue("manufacturer");
                        }
                    } else if (attributes.getValue("version") != null) {
                        if (result != null && result.chassis != null) {
                            result.chassis.version = attributes.getValue("version");
                        }
                    } else if (attributes.getValue("serial") != null) {
                        if (result != null && result.chassis != null) {
                            result.chassis.serial = attributes.getValue("serial");
                        }
                    } else if (attributes.getValue("asset") != null) {
                        if (result != null && result.chassis != null) {
                            result.chassis.asset = attributes.getValue("asset");
                        }
                    } else if (attributes.getValue("sku") != null) {
                        if (result != null && result.chassis != null) {
                            result.chassis.sku = attributes.getValue("sku");
                        }
                    }
                } else if (isProcessingProcessor) {
                    if (attributes.getValue("socket_destination") != null) {
                        if (result != null && result.processor != null) {
                            result.processor.socketDestination = attributes.getValue("socket_destination");
                        }
                    } else if (attributes.getValue("type") != null) {
                        if (result != null && result.processor != null) {
                            result.processor.type = attributes.getValue("type");
                        }
                    } else if (attributes.getValue("family") != null) {
                        if (result != null && result.processor != null) {
                            result.processor.family = attributes.getValue("family");
                        }
                    } else if (attributes.getValue("manufacturer") != null) {
                        if (result != null && result.processor != null) {
                            result.processor.manufacturer = attributes.getValue("manufacturer");
                        }
                    } else if (attributes.getValue("signature") != null) {
                        if (result != null && result.processor != null) {
                            result.processor.signature = attributes.getValue("signature");
                        }
                    } else if (attributes.getValue("version") != null) {
                        if (result != null && result.processor != null) {
                            result.processor.version = attributes.getValue("version");
                        }
                    } else if (attributes.getValue("external_clock") != null) {
                        if (result != null && result.processor != null) {
                            result.processor.externalClock = attributes.getValue("external_clock");
                        }
                    } else if (attributes.getValue("max_speed") != null) {
                        if (result != null && result.processor != null) {
                            result.processor.maxSpeed = attributes.getValue("max_speed");
                        }
                    } else if (attributes.getValue("status") != null) {
                        if (result != null && result.processor != null) {
                            result.processor.status = attributes.getValue("status");
                        }
                    } else if (attributes.getValue("serial_number") != null) {
                        if (result != null && result.processor != null) {
                            result.processor.serialNumber = attributes.getValue("serial_number");
                        }
                    } else if (attributes.getValue("part_number") != null) {
                        if (result != null && result.processor != null) {
                            result.processor.partNumber = attributes.getValue("part_number");
                        }
                    }

                } else if (isProcessingMemory) {
                    if (attributes.getValue("size") != null) {
                        if (result != null && result.memoryDevices != null && currentMemoryDevice != null) {
                            currentMemoryDevice.size = attributes.getValue("size");
                        }
                    } else if (attributes.getValue("form_factor") != null) {
                        if (result != null && result.memoryDevices != null && currentMemoryDevice != null) {
                            currentMemoryDevice.formFactor = attributes.getValue("form_factor");
                        }
                    } else if (attributes.getValue("locator") != null) {
                        if (result != null && result.memoryDevices != null && currentMemoryDevice != null) {
                            currentMemoryDevice.locator = attributes.getValue("locator");
                        }
                    } else if (attributes.getValue("bank_locator") != null) {
                        if (result != null && result.memoryDevices != null && currentMemoryDevice != null) {
                            currentMemoryDevice.bankLocator = attributes.getValue("bank_locator");
                        }
                    } else if (attributes.getValue("type") != null) {
                        if (result != null && result.memoryDevices != null && currentMemoryDevice != null) {
                            currentMemoryDevice.type = attributes.getValue("type");
                        }
                    } else if (attributes.getValue("type_detail") != null) {
                        if (result != null && result.memoryDevices != null && currentMemoryDevice != null) {
                            currentMemoryDevice.typeDetail = attributes.getValue("type_detail");
                        }
                    } else if (attributes.getValue("speed") != null) {
                        if (result != null && result.memoryDevices != null && currentMemoryDevice != null) {
                            currentMemoryDevice.speed = attributes.getValue("speed");
                        }
                    } else if (attributes.getValue("manufacturer") != null) {
                        if (result != null && result.memoryDevices != null && currentMemoryDevice != null) {
                            currentMemoryDevice.manufacturer = attributes.getValue("manufacturer");
                        }
                    } else if (attributes.getValue("serial_number") != null) {
                        if (result != null && result.memoryDevices != null && currentMemoryDevice != null) {
                            currentMemoryDevice.serialNumber = attributes.getValue("serial_number");
                        }
                    } else if (attributes.getValue("part_number") != null) {
                        if (result != null && result.memoryDevices != null && currentMemoryDevice != null) {
                            currentMemoryDevice.partNumber = attributes.getValue("part_number");
                        }
                    }
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) {
            if (qName.equalsIgnoreCase("memory_device")) {
                isProcessingBios = false;
                isProcessingSystem = false;
                isProcessingBaseBoard = false;
                isProcessingChassis = false;
                isProcessingProcessor = false;
                isProcessingMemory = false;
                if (result != null && result.memoryDevices != null) {
                    result.memoryDevices.add(currentMemoryDevice);
                    currentMemoryDevice = null;
                }
            }
        }
    }

    private String type;
    private Bios bios = new Bios();
    private System system = new System();
    private BaseBoard baseBoard = new BaseBoard();
    private Chassis chassis = new Chassis();
    private Processor processor = new Processor();
    private Set<MemoryDevice> memoryDevices = new HashSet<>();

    public BaseBoard getBaseBoard() {
        return this.baseBoard;
    }

    public Bios getBios() {
        return this.bios;
    }

    public Chassis getChassis() {
        return this.chassis;
    }

    public Set<MemoryDevice> getMemoryDevices() {
        return this.memoryDevices;
    }

    public Processor getProcessor() {
        return this.processor;
    }

    public System getSystem() {
        return this.system;
    }

    public String getType() {
        return this.type;
    }

    public static SystemInfo parseXML(final String outputAsXML) {
        String fileName = UUID.randomUUID().toString();
        SystemInfo result = null;
        try {
            final Path path = Files.createTempFile(fileName, ".xml");
            final File file = path.toFile();

            final FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(outputAsXML);
            fileWriter.flush();
            fileWriter.close();
            
            final SAXParserFactory factory = SAXParserFactory.newInstance();
            final SAXParser saxParser = factory.newSAXParser();
            final MapSystemInfoObjectHandlerSax handler = new MapSystemInfoObjectHandlerSax();
            saxParser.parse(file, handler);
            result = handler.getResult();
            
            file.delete();
        } catch (ParserConfigurationException | SAXException | IOException exception) {
            exception.printStackTrace();
            result = null;
        }

        // <sysinfo type='smbios'>
        // <bios>
        // <entry name='vendor'>American Megatrends Inc.</entry>
        // <entry name='version'>2002</entry>
        // <entry name='date'>07/28/2014</entry>
        // <entry name='release'>4.6</entry>
        // </bios>
        // <system>
        // <entry name='manufacturer'>ASUS</entry>
        // <entry name='product'>All Series</entry>
        // <entry name='version'>System Version</entry>
        // <entry name='serial'>System Serial Number</entry>
        // <entry name='uuid'>1f4ec9a0-d7da-11dd-9ed8-bcee7b8e142a</entry>
        // <entry name='sku'>All</entry>
        // <entry name='family'>ASUS MB</entry>
        // </system>
        // <baseBoard>
        // <entry name='manufacturer'>ASUSTeK COMPUTER INC.</entry>
        // <entry name='product'>H87-PLUS</entry>
        // <entry name='version'>Rev X.0x</entry>
        // <entry name='serial'>131219362700950</entry>
        // <entry name='asset'>To be filled by O.E.M.</entry>
        // <entry name='location'>To be filled by O.E.M.</entry>
        // </baseBoard>
        // <chassis>
        // <entry name='manufacturer'>Chassis Manufacture</entry>
        // <entry name='version'>Chassis Version</entry>
        // <entry name='serial'>Chassis Serial Number</entry>
        // <entry name='asset'>Asset-1234567890</entry>
        // <entry name='sku'>To be filled by O.E.M.</entry>
        // </chassis>
        // <processor>
        // <entry name='socket_destination'>SOCKET 1150</entry>
        // <entry name='type'>Central Processor</entry>
        // <entry name='family'>Other</entry>
        // <entry name='manufacturer'>Intel</entry>
        // <entry name='signature'>Type 0, Family 6, Model 60, Stepping 3</entry>
        // <entry name='version'>Intel(R) Pentium(R) CPU G3450 @ 3.40GHz</entry>
        // <entry name='external_clock'>100 MHz</entry>
        // <entry name='max_speed'>3900 MHz</entry>
        // <entry name='status'>Populated, Enabled</entry>
        // <entry name='serial_number'>Not Specified</entry>
        // <entry name='part_number'>Fill By OEM</entry>
        // </processor>
        // <memory_device>
        // <entry name='size'>8 GB</entry>
        // <entry name='form_factor'>DIMM</entry>
        // <entry name='locator'>ChannelA-DIMM0</entry>
        // <entry name='bank_locator'>BANK 0</entry>
        // <entry name='type'>DDR3</entry>
        // <entry name='type_detail'>Synchronous</entry>
        // <entry name='speed'>1600 MT/s</entry>
        // <entry name='manufacturer'>0215</entry>
        // <entry name='serial_number'>00000000</entry>
        // <entry name='part_number'>CMZ16GX3M2A1600C10</entry>
        // </memory_device>
        // <memory_device>
        // <entry name='size'>8 GB</entry>
        // <entry name='form_factor'>DIMM</entry>
        // <entry name='locator'>ChannelA-DIMM1</entry>
        // <entry name='bank_locator'>BANK 1</entry>
        // <entry name='type'>DDR3</entry>
        // <entry name='type_detail'>Synchronous</entry>
        // <entry name='speed'>1600 MT/s</entry>
        // <entry name='manufacturer'>0215</entry>
        // <entry name='serial_number'>00000000</entry>
        // <entry name='part_number'>CMZ16GX3M2A1600C10</entry>
        // </memory_device>
        // <memory_device>
        // <entry name='size'>8 GB</entry>
        // <entry name='form_factor'>DIMM</entry>
        // <entry name='locator'>ChannelB-DIMM0</entry>
        // <entry name='bank_locator'>BANK 2</entry>
        // <entry name='type'>DDR3</entry>
        // <entry name='type_detail'>Synchronous</entry>
        // <entry name='speed'>1600 MT/s</entry>
        // <entry name='manufacturer'>0215</entry>
        // <entry name='serial_number'>00000000</entry>
        // <entry name='part_number'>CMZ16GX3M2A1600C10</entry>
        // </memory_device>
        // <memory_device>
        // <entry name='size'>8 GB</entry>
        // <entry name='form_factor'>DIMM</entry>
        // <entry name='locator'>ChannelB-DIMM1</entry>
        // <entry name='bank_locator'>BANK 3</entry>
        // <entry name='type'>DDR3</entry>
        // <entry name='type_detail'>Synchronous</entry>
        // <entry name='speed'>1600 MT/s</entry>
        // <entry name='manufacturer'>0215</entry>
        // <entry name='serial_number'>00000000</entry>
        // <entry name='part_number'>CMZ16GX3M2A1600C10</entry>
        // </memory_device>
        // <oemStrings>
        // <entry>To Be Filled By O.E.M.
        // </entry>
        // <entry>To Be Filled By O.E.M.
        // </entry>
        // <entry>Amber</entry>
        // <entry>To Be Filled By O.E.M.
        // </entry>
        // </oemStrings>
        // </sysinfo>
        return result;
    }
}
