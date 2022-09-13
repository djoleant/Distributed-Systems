using System.ServiceModel;

[ServiceContract]
public class nazivKlase
{
    [OperationContract]
    public void NekaMetoda()
    {
        /**/
    }
    /**/
}

[DataContract]
public class CekaonicaStatus
{
    [DataMember]
    public Guid LekarUiD { get; set; }

    [DataMember]
    public string Pacijent { get; set; }

    [DataMember]
    public Status status { get; set; }
}
