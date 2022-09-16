using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Messaging;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace AV.MSMQ
{
    public partial class FormMain : Form
    {
        //Communication comm;
        MessageQueue channel;

        public FormMain()
        {
            InitializeComponent();
        }

        private void btnSend_Click(object sender, EventArgs e)
        {
            try
            {
                errorProvider.SetError(txtMessage, "");
                channel.Send(txtMessage.Text);
            }
            catch (Exception exc)
            {
                errorProvider.SetError(txtMessage, exc.ToString());
            }
        }

        private void btnReset_Click(object sender, EventArgs e)
        {
            StopQueue();
            StartQueue(txtPath.Text, Messaging_ReceiveCompleted);
        }

        private void btnStop_Click(object sender, EventArgs e)
        {
            StopQueue();
        }

        private void btnStart_Click(object sender, EventArgs e)
        {
            StartQueue(txtPath.Text, Messaging_ReceiveCompleted);
        }
        public void StartQueue(string path, ReceiveCompletedEventHandler completedHandler)
        {
            try
            {
                DisplayMessage(">>> Starting Queue! <<<", Color.DarkGreen);

                channel = new MessageQueue(path);
                //channel.Path = path;
                if (MessageQueue.Exists(channel.Path))
                {
                    //Exists

                }
                else
                {
                    MessageQueue.Create(channel.Path);
                }

                channel.Formatter = new XmlMessageFormatter(new Type[] { typeof(string) });
                channel.MulticastAddress = txtMulticast.Text;// "234.1.1.1:8001";

                channel.ReceiveCompleted += completedHandler;

                channel.BeginReceive();
                DisplayMessage(string.Format(">>> QUEUE [{0}] STARTED <<<", path), Color.DarkGreen);
            }
            catch (Exception exc)
            {
                DisplayMessage(exc.ToString(), Color.OrangeRed);
            }
        }

        public void StopQueue()
        {
            try
            {
                channel.ReceiveCompleted -= Messaging_ReceiveCompleted;
                channel.Close();

                channel.Dispose();

                DisplayMessage(">>> QUEUE STOPED <<<", Color.Red);
            }
            catch (Exception exc)
            {
                DisplayMessage(exc.ToString(), Color.OrangeRed);
            }
        }

        private void Messaging_ReceiveCompleted(object sender, ReceiveCompletedEventArgs e)
        {
            try
            {
                var msg = channel.EndReceive(e.AsyncResult);
                string data = msg.Body.ToString();

                DisplayMessage(data);

                channel.BeginReceive();
            }
            catch (MessageQueueException qexception)
            {
                DisplayMessage(qexception.ToString(), Color.OrangeRed);
            }
        }
        public void DisplayMessage(string message)
        {
            DisplayMessage(message, Color.Black);
        }

        public void DisplayMessage(string message, Color color)
        {
            if (rtfReciver.InvokeRequired)
            {
                rtfReciver.Invoke(new MethodInvoker(delegate () { DisplayMessage(message, color); }));
            }
            else
            {
                AppendText(rtfReciver, message, color, true);
            }
        }

        public void AppendText(RichTextBox box, string text, Color color, bool addNewLine = false)
        {
            box.SuspendLayout();
            Color old = box.SelectionColor;
            box.SelectionColor = color;
            box.AppendText(addNewLine
                ? $"{text}{Environment.NewLine}"
                : text);
            box.ScrollToCaret();
            box.SelectionColor = old;
            box.ResumeLayout();
        }

        private void btnFindAllQueues_Click(object sender, EventArgs e)
        {
            lvQueues.SuspendLayout();
            try
            {
                var queues = MessageQueue.GetMessageQueueEnumerator();
                //foreach (var q in queues)
                while (queues.MoveNext())
                {
                    lvQueues.Items.Add(new ListViewItem(queues.Current.QueueName)
                    {
                        Tag = queues.Current
                    });
                }
            }
            catch (Exception exc)
            {
                errorProvider.SetError(btnFindAllQueues, exc.Message);
                DisplayMessage(exc.ToString(), Color.DarkViolet);
            }
            finally
            {
                lvQueues.ResumeLayout();
                lvQueues.PerformLayout();
            }
        }
    }
}
