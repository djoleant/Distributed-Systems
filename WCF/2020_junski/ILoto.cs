using System;
using System.Collection.Generic;
using System.Linq;
using System.Runtime.Serialization;
using System.ServiceModel;
using System.Text;

namespace loto
{
    [
        ServiceContract(
            SessionMode = SessionMode.Required,
            CallbackContract = typeof (ILotoCallback))
    ]
    public interface ILoto
    {
        [OperationContract(IsOneWay = false)]
        int NovaKombinacija(string nadimak, Kombinacija k);

        [OperationContract(IsOneWay = false)]
        bool UkloniKombinaciju(string nadimak, int ID);

        [OperationContract(IsOneWay = true)]
        void IzvuciBroj(string adminPass, int broj);
    }

    [DataContract]
    public class Korisnik
    {
        [DataMember]
        public string nadimak { get; set; }

        [DataMember]
        public ILotoCallback Callback { get; set; }

        [DataMember]
        public Dictionary<int, Kombinacija> Kombinacije { get; set; }

        public Korisnik()
        {
            Kombinacije = new Dictionary<int, Kombinacija>();
        }
    }

    [DataContract]
    public class Kombinacija
    {
        [DataMember]
        public int ID { get; set; }

        [DataMember]
        public List<int> Brojevi { get; set; }

        public Kombinacija()
        {
            Brojevi = new List<int>();
        }
    }
}
